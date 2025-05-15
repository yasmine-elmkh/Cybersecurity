package com.revature.daos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.SecurityEvent;

public interface SecurityEventRepository extends JpaRepository<SecurityEvent, Long> {
    List<SecurityEvent> findByType(String type);
    long countByType(String type);
     List<SecurityEvent> findByTimestampAfter(LocalDateTime time);
  
  @Query("SELECT e FROM SecurityEvent e ORDER BY e.timestamp DESC")
    List<SecurityEvent> findRecentLogs();

    @Query("SELECT e.type, COUNT(e) FROM SecurityEvent e GROUP BY e.type")
    List<Object[]> getChartData();

    @Query("SELECT FUNCTION('DATE', e.timestamp), COUNT(e) FROM SecurityEvent e GROUP BY FUNCTION('DATE', e.timestamp)")
    List<Object[]> getHeatmapData();

    @Query("SELECT FUNCTION('HOUR', e.timestamp), COUNT(e) FROM SecurityEvent e GROUP BY FUNCTION('HOUR', e.timestamp)")
    List<Object[]> getTrendData();
}