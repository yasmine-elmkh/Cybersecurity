package com.revature.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.models.Packet;
import com.revature.services.PacketService;

@RestController
@RequestMapping("/api/packets")
public class PacketController {

    private final PacketService packetService;

    public PacketController(PacketService packetService) {
        this.packetService = packetService;
    }

    @GetMapping
    public List<Packet> getAllPackets(@RequestParam(required = false) Long sessionId) {
        if (sessionId != null) {
            return packetService.findBySessionId(sessionId);
        }
        return packetService.findAll();
    }

    @GetMapping("/session/{sessionId}")
    public List<Packet> getPacketsBySessionId(@PathVariable Long sessionId) {
        return packetService.findBySessionId(sessionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Packet> getPacketById(@PathVariable Integer id) {
        return packetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Packet createPacket(@RequestBody Packet packet) {
        return packetService.create(packet);
    }

    @PutMapping("/{id}")
    public Packet updatePacket(@PathVariable Integer id, @RequestBody Packet packet) {
        return packetService.update(id, packet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacket(@PathVariable Integer id) {
        packetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

