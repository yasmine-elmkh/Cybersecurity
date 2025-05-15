package com.revature.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionSummaryDTO {
    private Long sessionId;
    private String interfaceName;
    private String filterExp;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private int packetCount;
    private int anomalyCount;
}
