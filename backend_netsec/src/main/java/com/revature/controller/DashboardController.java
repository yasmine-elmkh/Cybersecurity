package com.revature.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.SecurityEventRepository;
import com.revature.models.SecurityEvent;

@RestController
@RequestMapping("/api")
public class DashboardController {

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


@GetMapping("/stats/heatmap")
    public Map<String, Object> getHeatmap() {
        Map<String, Object> heatmap = new HashMap<>();
        heatmap.put("heatmapData", repo.getHeatmapData());
        return heatmap;
    }

    @GetMapping("/stats/trends")
    public Map<String, Object> getTrends() {
        Map<String, Object> trends = new HashMap<>();
        trends.put("trendData", repo.getTrendData());
        return trends;
    }

    @GetMapping("/events")
    public List<SecurityEvent> getEvents() {
        return repo.findAll();
    }

    @GetMapping("/logs")
    public List<SecurityEvent> getLogs() {
        return repo.findRecentLogs();
    }
}