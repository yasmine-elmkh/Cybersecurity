package com.revature.services;

import com.revature.daos.PacketDAO;
import com.revature.models.Packet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacketService {

    private final PacketDAO packetDAO;


    public PacketService(PacketDAO packetDAO) {
        this.packetDAO = packetDAO;
    }

    public List<Packet> findAll() {
        return packetDAO.findAll();
    }

    public Optional<Packet> findById(Integer id) {
        return packetDAO.findById(id);
    }

    public List<Packet> findBySessionId(Long sessionId) {
        return packetDAO.findBySessionSessionIdOrderByCapturedAtAsc(sessionId);
    }

    public Packet create(Packet packet) {
        return packetDAO.save(packet);
    }

    public Packet update(Integer id, Packet updated) {
        return packetDAO.findById(id)
                .map(existing -> {
                    existing.setCapturedAt(updated.getCapturedAt());
                    existing.setSrcIp(updated.getSrcIp());
                    existing.setDstIp(updated.getDstIp());
                    existing.setSrcPort(updated.getSrcPort());
                    existing.setDstPort(updated.getDstPort());
                    existing.setProtocol(updated.getProtocol());
                    existing.setLength(updated.getLength());
                    existing.setRawData(updated.getRawData());
                    return packetDAO.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Packet not found: " + id));
    }

    public void delete(Integer id) {
        packetDAO.deleteById(id);
    }
}
