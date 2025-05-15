package com.revature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .cors().and().csrf().disable()

          // 1) Déclare quels chemins sont publics
          .authorizeHttpRequests(auth -> auth
              .requestMatchers(
                "/",                // ton index.html
                "/static/**",       // js, css, images…
                "/favicon.ico"
              ).permitAll()
              // 2) protège tes API
              .requestMatchers("/api/**").hasRole("ADMIN")
              .anyRequest().authenticated()
          )
          // 3) Garde le login de Spring par défaut mais FORCE la redirection après succès
          .formLogin(form -> form
              // si tu as une page custom : .loginPage("/login").permitAll()
              .permitAll()
              .defaultSuccessUrl("/", true)  // ← ici, on redirige VERS "/" quoiqu’il arrive
          )
          .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // ou BCryptPasswordEncoder en prod
    }
}