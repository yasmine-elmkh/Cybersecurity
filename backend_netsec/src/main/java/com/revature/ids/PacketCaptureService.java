package com.revature.ids;
//un service de capture et d’analyse de paquets réseau en temps réel, 
//basé sur la librairie Pcap4J. 
import org.pcap4j.core.*;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.revature.models.Anomaly;
import com.revature.services.AnomalyService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class PacketCaptureService  implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired private RuleLoader ruleLoader;
    @Autowired private AnomalyService anomalyService;
    @Autowired private IpBlocker ipBlocker;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            startCapture();  // ⬅️ Exécuté après que le contexte est prêt
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startCapture() throws Exception {
        System.out.println("▶ startCapture() called");

        PcapNetworkInterface nif = Pcaps.getDevByName("en0");
if (nif == null) {
    throw new IllegalStateException("Interface non trouvée ! Vérifie le nom.");
}
PcapHandle handle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10);
handle.setFilter("not port 138 and not port 137 and not port 53", BpfProgram.BpfCompileMode.OPTIMIZE);


System.out.println("▶ Using interface: " + nif.getName());
        // 4) Lance la capture dans un thread séparé
        new Thread(() -> {
            try {
                // capture 10 paquets max, ou jusqu'à breakLoop()
                handle.loop(-1, this::analyzePacket);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                handle.close();
            }
        }).start();
    }
private final Map<String, Long> ipAlertTimestamps = new HashMap<>();
private static final long ALERT_COOLDOWN_MS = 60_000; // 1 minute

   private void analyzePacket(Packet packet) {
        System.out.println("Captured packet: " + packet);

    String srcIp = null;
    String payload = packet.getPayload() != null ? packet.getPayload().toString() : "";

    // === IPv4 ===
    IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
    if (ipV4Packet != null) {
        srcIp = ipV4Packet.getHeader().getSrcAddr().getHostAddress();
    }
    
    // === IPv6 ===
    else {
        IpV6Packet ipV6Packet = packet.get(IpV6Packet.class);
        if (ipV6Packet != null) {
            srcIp = ipV6Packet.getHeader().getSrcAddr().getHostAddress();
        }
    }

    // === ARP ===
    if (srcIp == null) {
        ArpPacket arpPacket = packet.get(ArpPacket.class);
        if (arpPacket != null) {
            srcIp = arpPacket.getHeader().getSrcProtocolAddr().getHostAddress();
        }
    }
    long now = System.currentTimeMillis();
 if (ipAlertTimestamps.containsKey(srcIp)) {
        long last = ipAlertTimestamps.get(srcIp);
        if (now - last < ALERT_COOLDOWN_MS) {
            return; // Ignore pour éviter le spam
        }
    }

    ipAlertTimestamps.put(srcIp, now); // Enregistre le moment de l'alerte

System.out.printf("→ IP: %s | Payload: %s%n", srcIp, payload);

    // Aucun IP détecté → on ignore le paquet
    if (srcIp == null) return;

    for (Rule r : ruleLoader.getRules()) {
        if (!payload.toLowerCase().contains(r.getPattern().toLowerCase())) continue;  // pattern

        if (payload.contains(r.getPattern())) {
            anomalyService.create(new Anomaly(
                null,
                srcIp,
                r.getSid(),
                r.getMessage(),
                LocalDateTime.now()
            ));
            if (!ipBlocker.isBlocked(srcIp)) {
                ipBlocker.blockIp(srcIp);
            }
            break;
        }
        System.out.println("🚨 ALERTE : " + r.getMessage() + " depuis " + srcIp);
        System.out.println("⛔ IP bloquée : " + srcIp);

    }
}

}
