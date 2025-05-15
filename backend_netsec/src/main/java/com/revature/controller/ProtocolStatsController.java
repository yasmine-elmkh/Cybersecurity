package com.revature.controller;

import com.revature.models.ProtocolStats;
import com.revature.services.ProtocolStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/protocol-stats")
public class ProtocolStatsController {
    private final ProtocolStatsService statsService;

    public ProtocolStatsController(ProtocolStatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public List<ProtocolStats> getAllStats(@RequestParam(required = false) Long sessionId) {
        if (sessionId != null) {
            return statsService.findBySessionId(sessionId);
        }
        return statsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProtocolStats> getStatById(@PathVariable Integer id) {
        return statsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProtocolStats createStats(@RequestBody ProtocolStats stats) {
        return statsService.create(stats);
    }

    @PutMapping("/{id}")
    public ProtocolStats updateStats(@PathVariable Integer id, @RequestBody ProtocolStats stats) {
        return statsService.update(id, stats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStats(@PathVariable Integer id) {
        statsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
