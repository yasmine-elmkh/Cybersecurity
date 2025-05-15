package com.revature.services;

import com.revature.daos.CaptureSessionDAO;
import com.revature.models.CaptureSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaptureSessionService {

    private final CaptureSessionDAO captureSessionDAO;

    public CaptureSessionService(CaptureSessionDAO captureSession) {
        this.captureSessionDAO = captureSession;
    }

    public List<CaptureSession> findAll() {
        return captureSessionDAO.findAll();
    }

    public Optional<CaptureSession> findById(Integer id) {
        return captureSessionDAO.findById(id);
    }

    public List<CaptureSession> findByInterfaceName(String iface) {
        return captureSessionDAO.findByInterfaceName(iface);
    }

    public CaptureSession create(CaptureSession session) {
        return captureSessionDAO.save(session);
    }

    public CaptureSession update(Integer id, CaptureSession updated) {
        return captureSessionDAO.findById(id)
                .map(existing -> {
                    existing.setInterfaceName(updated.getInterfaceName());
                    existing.setFilterExp(updated.getFilterExp());
                    existing.setEndedAt(updated.getEndedAt());
                    return captureSessionDAO.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Session not found: " + id));
    }

    public void delete(Integer id) {
        captureSessionDAO.deleteById(id);
    }
}
