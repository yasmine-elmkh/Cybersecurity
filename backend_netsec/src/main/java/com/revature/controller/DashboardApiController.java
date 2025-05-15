// package com.revature.controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import java.util.*;

// @RestController
// @RequestMapping("/api")
// public class DashboardApiController {

//     @GetMapping("/stats")
//     public Map<String, Object> getStats() {
//         Map<String, Object> stats = new HashMap<>();
//         stats.put("totalAlerts", 12);
//         stats.put("activeThreats", 2);
//         stats.put("blockedIPs", 1);
//         return stats;
//     }

//     @GetMapping("/events")
//     public List<Map<String, Object>> getEvents() {
//         List<Map<String, Object>> events = new ArrayList<>();
//         Map<String, Object> e1 = new HashMap<>();
//         e1.put("id", 1);
//         e1.put("type", "IDS_ALERT");
//         e1.put("description", "Tentative d'intrusion détectée");
//         e1.put("ipAddress", "192.168.1.10");
//         e1.put("timestamp", new Date());
//         events.add(e1);
//         return events;
//     }

//     @GetMapping("/charts")
//     public Map<String, Object> getCharts() {
//         Map<String, Object> charts = new HashMap<>();
//         charts.put("timeline", List.of(
//             Map.of("timestamp", "2024-06-01", "value", 5, "type", "ALERT"),
//             Map.of("timestamp", "2024-06-02", "value", 7, "type", "ALERT")
//         ));
//         charts.put("byType", List.of(
//             Map.of("type", "ALERT", "value", 12),
//             Map.of("type", "THREAT", "value", 2)
//         ));
//         return charts;
//     }

//     @GetMapping("/stats/trends")
//     public List<Map<String, Object>> getTrends() {
//         return List.of(
//             Map.of("timestamp", "2024-06-01", "value", 5, "type", "ALERT"),
//             Map.of("timestamp", "2024-06-02", "value", 7, "type", "ALERT")
//         );
//     }

//     @GetMapping("/stats/heatmap")
//     public List<Map<String, Object>> getHeatmap() {
//         return List.of(
//             Map.of("day", "Lundi", "hour", 10, "value", 2),
//             Map.of("day", "Lundi", "hour", 11, "value", 3),
//             Map.of("day", "Mardi", "hour", 10, "value", 1)
//         );
//     }
// }
