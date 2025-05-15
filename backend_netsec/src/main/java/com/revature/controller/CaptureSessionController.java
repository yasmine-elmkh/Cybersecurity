package com.revature.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.SessionSummaryDTO;
import com.revature.models.CaptureSession;
import com.revature.services.CaptureSessionService;

@RestController
@RequestMapping("/api/sessions")
public class CaptureSessionController {

    private final CaptureSessionService captureSessionService;

    public CaptureSessionController(CaptureSessionService captureSessionService) {
        this.captureSessionService = captureSessionService;
    }

    @GetMapping
    public List<SessionSummaryDTO> getAllSessions(@RequestParam(required = false) String interfaceName) {
        List<CaptureSession> sessions;
        if (interfaceName != null) {
            sessions = captureSessionService.findByInterfaceName(interfaceName);
        } else {
            sessions = captureSessionService.findAll();
        }

        return sessions.stream()
                .map(session -> new SessionSummaryDTO(
                        session.getSessionId(),
                        session.getInterfaceName(),
                        session.getFilterExp(),
                        session.getStartedAt(),
                        session.getEndedAt(),
                        session.getPackets().size(),
                        session.getAnomalies().size()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaptureSession> getSessionById(@PathVariable Integer id) {
        return captureSessionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CaptureSession createSession(@RequestBody CaptureSession session) {
        return captureSessionService.create(session);
    }

    @PutMapping("/{id}")
    public CaptureSession updateSession(@PathVariable Integer id, @RequestBody CaptureSession session) {
        return captureSessionService.update(id, session);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Integer id) {
        captureSessionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
