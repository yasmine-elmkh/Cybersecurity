package com.revature.ids;

import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class IpBlocker {

    private Set<String> blocked = new HashSet<>();

   public void blockIp(String ip) {
    try {
        String os = System.getProperty("os.name").toLowerCase();
        Process process;

        if (os.contains("win")) {
            // Pour Windows : commande pour ajouter une règle de pare-feu (exemple)
            String command = "netsh advfirewall firewall add rule name=\"BlockIP" + ip + "\" dir=in action=block remoteip=" + ip;
            process = Runtime.getRuntime().exec(new String[]{"cmd", "/c", command});
        } else {
            // Pour Linux/macOS : utiliser iptables (ou autre selon ta config)
            String command = "iptables -A INPUT -s " + ip + " -j DROP";
            process = Runtime.getRuntime().exec(new String[]{"sh", "-c", command});
        }

        process.waitFor();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public boolean isBlocked(String ip) {
        return blocked.contains(ip);
    }
}
