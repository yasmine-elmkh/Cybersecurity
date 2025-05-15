package com.revature.ids;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.revature.daos.AnomalyDAO;
import com.revature.daos.SecurityEventRepository;
import com.revature.models.Anomaly;
import com.revature.models.SecurityEvent;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SuspiciousActivityDetector implements HandlerInterceptor {

    private final AnomalyDAO anomalyRepository;

  private final SecurityEventRepository securityEventRepository;

public SuspiciousActivityDetector(AnomalyDAO anomalyRepository, SecurityEventRepository securityEventRepository) {
    this.anomalyRepository = anomalyRepository;
    this.securityEventRepository = securityEventRepository;
}

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        String ip = req.getRemoteAddr();
        String uri = req.getRequestURI();
        String query = req.getQueryString();

       if (query != null && query.contains("<script>")) {
    anomalyRepository.save(new Anomaly(null, ip, "XSS", "Tentative d'injection XSS sur " + uri, LocalDateTime.now()));
    
    SecurityEvent event = new SecurityEvent();
    event.setType("IDS_ALERT");
    event.setDescription("Tentative XSS détectée sur : " + uri);
    event.setIpAddress(ip);
    event.setTimestamp(LocalDateTime.now());
    securityEventRepository.save(event);
}

        if (uri.contains("/login") && req.getMethod().equals("POST")) {
            // Simuler brute force par nombre d’essais
            trackLoginAttempts(ip);
        }

        return true;
    }

    private Map<String, Integer> loginAttempts = new HashMap<>();

    private void trackLoginAttempts(String ip) {
        int attempts = loginAttempts.getOrDefault(ip, 0) + 1;
        loginAttempts.put(ip, attempts);

      if (attempts > 5) {
    anomalyRepository.save(new Anomaly(null, ip, "Brute-force", "Plus de 5 tentatives de login", LocalDateTime.now()));
    
    SecurityEvent event = new SecurityEvent();
    event.setType("IDS_ALERT");
    event.setDescription("Brute force détecté sur login");
    event.setIpAddress(ip);
    event.setTimestamp(LocalDateTime.now());
    securityEventRepository.save(event);
}

        
    }


    private static final Logger logger = LoggerFactory.getLogger(SuspiciousActivityDetector.class);

    public static void logSuspiciousActivity(String ip, String reason) {
        logger.warn("Suspicious activity detected from IP {}: {}", ip, reason);
    
}



}
 
