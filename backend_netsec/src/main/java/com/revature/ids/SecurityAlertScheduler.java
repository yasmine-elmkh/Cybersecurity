// package com.revature.ids;

// import java.time.LocalDateTime;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;

// import com.revature.daos.SecurityEventRepository;

// @Component
// public class SecurityAlertScheduler {

//     @Autowired
//     private SecurityEventRepository repo;

//     @Autowired
//     private JavaMailSender mailSender;

//     @Scheduled(fixedRate = 3600000) // Chaque heure
//     public void checkThresholds() {
//         long idsCount = repo.findByType("IDS_ALERT").stream()
//             .filter(e -> e.getTimestamp().isAfter(LocalDateTime.now().minusHours(1)))
//             .count();

//         if (idsCount > 10) {
//             sendEmailAlert(idsCount);
//         }
//     }

//     private void sendEmailAlert(long count) {
//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setTo("admin@example.com");
//         message.setSubject("Alerte IDS");
//         message.setText("Nombre élevé d’alertes IDS détectées : " + count);
//         mailSender.send(message);
//     }
// }
