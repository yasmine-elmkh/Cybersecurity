package com.revature.services;

import com.revature.daos.AnomalyDAO;
import com.revature.models.Anomaly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AnomalyService {

    private final AnomalyDAO anomalyDAO;

    @Autowired
    public AnomalyService(AnomalyDAO anomalyDAO) {
        this.anomalyDAO = anomalyDAO;
    }

    public List<Anomaly> findAll() {
        return anomalyDAO.findAll();
    }

    public Optional<Anomaly> findById(Integer id) {
        return anomalyDAO.findById(id);
    }

    public List<Anomaly> findByType(String type) {
        return anomalyDAO.findByAnomalyType(type);
    }

    public List<Anomaly> findBySessionId(Integer sessionId) {
        return anomalyDAO.findBySessionSessionId(sessionId);
    }

    public Anomaly create(Anomaly anomaly) {
        return anomalyDAO.save(anomaly);
    }

    public Anomaly update(Integer id, Anomaly updated) {
        return anomalyDAO.findById(id)
                .map(existing -> {
                    existing.setAnomalyType(updated.getAnomalyType());
                    existing.setDescription(updated.getDescription());
                    return anomalyDAO.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Anomaly not found: " + id));
    }

    public void delete(Integer id) {
        anomalyDAO.deleteById(id);
    }

}
