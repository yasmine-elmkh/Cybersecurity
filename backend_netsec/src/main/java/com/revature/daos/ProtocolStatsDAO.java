package com.revature.daos;


import com.revature.models.ProtocolStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProtocolStatsDAO extends JpaRepository<ProtocolStats, Integer> {
    List<ProtocolStats> findBySessionSessionId(Long sessionId);
}
