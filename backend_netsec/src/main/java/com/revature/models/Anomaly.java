package com.revature.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "anomaly")
public class Anomaly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long anomalyId;

    private String ip;
    @ManyToOne
    @JoinColumn(name = "packet_id", nullable = true)
    @JsonIgnore
    private Packet packet;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = true)
    @JsonBackReference
    private CaptureSession session;

    private String anomalyType;
    private String description;
    @CreationTimestamp
    private LocalDateTime detectedAt;


    public Anomaly(Long id, String ip, String eventType, String description, LocalDateTime timestamp) {
        this.anomalyId = id;
        this.ip = ip;
        this.anomalyType = eventType;
        this.description = description;
        this.detectedAt = timestamp;
    }
        // Getters et Setters
    public Long getId() { return anomalyId; }
    public void setId(Long id) { this.anomalyId = id; }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }


    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }



}
