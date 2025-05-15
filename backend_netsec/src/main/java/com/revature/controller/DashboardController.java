package com.revature.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.SecurityEventRepository;
import com.revature.models.SecurityEvent;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @GetMapping("/stats")
public Map<String, Object> getStats() {
    // Exemple de données factices, à adapter selon ton modèle
    Map<String, Object> stats = new HashMap<>();
    stats.put("loginFailures", 5);
    stats.put("idsAlerts", 3);
    stats.put("eventsLast24h", 12);
    return stats;
}


    @Autowired
    private SecurityEventRepository repo;


@GetMapping("/charts")
public Map<String, Object> getCharts() {
    List<Object[]> rawData = repo.getChartData();
    Map<String, Long> chartData = new HashMap<>();
    for (Object[] obj : rawData) {
        chartData.put((String) obj[0], (Long) obj[1]);
    }
    return Map.of("chartData", chartData);
}
// ...existing code...

@PostMapping("/anomalies/block")
public ResponseEntity<?> blockIp(@RequestBody Map<String, String> body) {
    String ip = body.get("ip");
    // Ajoute ici la logique de blocage
    return ResponseEntity.ok(Map.of("status", "blocked", "ip", ip));
}

// ...existing code...


@GetMapping("/stats/heatmap")
    public Map<String, Object> getHeatmap() {
        Map<String, Object> heatmap = new HashMap<>();
        heatmap.put("heatmapData", repo.getHeatmapData());
        return heatmap;
    }

    // @GetMapping("/stats/trends")
    // public Map<String, Object> getTrends() {
    //     Map<String, Object> trends = new HashMap<>();
    //     trends.put("trendData", repo.getTrendData());
    //     return trends;
    // }

    @GetMapping("/events")
    public List<SecurityEvent> getEvents() {
        return repo.findAll();
    }

    @GetMapping("/logs")
    public List<SecurityEvent> getLogs() {
        return repo.findRecentLogs();
    }


}