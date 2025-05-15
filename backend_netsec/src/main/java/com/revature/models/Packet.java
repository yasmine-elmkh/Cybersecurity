package com.revature.models;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.util.ByteArrayConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "packet")
public class Packet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packetId;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    @JsonBackReference
    private CaptureSession session;

    @CreationTimestamp
    private OffsetDateTime capturedAt;

    @Column(columnDefinition = "INET")
    private String srcIp;

    @Column(columnDefinition = "INET")
    private String dstIp;

    private Integer srcPort;
    private Integer dstPort;

    public enum ProtocolType {
        TCP, UDP, ICMP // Must be uppercase to match SQL inserts
    }

    // In Packet class:
    @Enumerated(EnumType.STRING)
    private ProtocolType protocol; // Instead of String

    private Integer length;

    // In Packet class:
    @Convert(converter = ByteArrayConverter.class)
    private byte[] rawData;
}
