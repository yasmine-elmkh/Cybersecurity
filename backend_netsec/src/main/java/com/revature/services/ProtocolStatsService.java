package com.revature.services;

import com.revature.daos.ProtocolStatsDAO;
import com.revature.models.ProtocolStats;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProtocolStatsService {

    private final ProtocolStatsDAO protocolStatsDAO;


    public ProtocolStatsService(ProtocolStatsDAO protocolStatsDAO) {
        this.protocolStatsDAO = protocolStatsDAO;
    }

    public List<ProtocolStats> findAll() {
        return protocolStatsDAO.findAll();
    }

    public Optional<ProtocolStats> findById(Integer id) {
        return protocolStatsDAO.findById(id);
    }

    public List<ProtocolStats> findBySessionId(Long sessionId) {
        return protocolStatsDAO.findBySessionSessionId(sessionId);
    }

    public ProtocolStats create(ProtocolStats stats) {
        return protocolStatsDAO.save(stats);
    }

    public ProtocolStats update(Integer id, ProtocolStats updated) {
        return protocolStatsDAO.findById(id)
                .map(existing -> {
                    existing.setProtocol(updated.getProtocol());
                    existing.setPacketCount(updated.getPacketCount());
                    existing.setByteCount(updated.getByteCount());
                    return protocolStatsDAO.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("ProtocolStats not found: " + id));
    }

    public void delete(Integer id) {
        protocolStatsDAO.deleteById(id);
    }
}
