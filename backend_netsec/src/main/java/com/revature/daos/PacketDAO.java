package com.revature.daos;

import com.revature.models.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacketDAO extends JpaRepository<Packet, Integer> {
    List<Packet> findBySessionSessionIdOrderByCapturedAtAsc(Long sessionId);
}
