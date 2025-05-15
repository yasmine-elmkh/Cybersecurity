package com.revature.daos;

import com.revature.models.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//Annotation Spring qui indique que cette interface est un composant de type "Repository", c’est-à-dire :
//Qu'elle sert à accéder à la base de données.Et que Spring va automatiquement la détecter et injecter là où tu en as besoin.


@Repository
public interface AnomalyDAO extends JpaRepository<Anomaly, Integer> {
    List<Anomaly> findByIp(String ip);
    List<Anomaly> findByAnomalyType(String anomalyType);
    List<Anomaly> findBySessionSessionId(Integer sessionId);
}
