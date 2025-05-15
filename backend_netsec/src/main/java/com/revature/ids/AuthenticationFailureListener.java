package com.revature.ids;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.revature.daos.SecurityEventRepository;
import com.revature.models.SecurityEvent;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private SecurityEventRepository repository;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        SecurityEvent ev = new SecurityEvent();
        ev.setType("LOGIN_FAILURE");
        ev.setDescription("Échec de connexion pour l'utilisateur : " + event.getAuthentication().getName());
        ev.setIpAddress("192.168.1.6"); 
        ev.setTimestamp(LocalDateTime.now());
        repository.save(ev);
    }
}
