package com.revature.daos;

import com.revature.models.CaptureSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaptureSessionDAO extends JpaRepository<CaptureSession, Integer> {
    List<CaptureSession> findByInterfaceName(String interfaceName);
}
