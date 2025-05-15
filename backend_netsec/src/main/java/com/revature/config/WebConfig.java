// package com.revature.config;
// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;
// @Configuration
// public class WebConfig {
//   @Bean
//   public FilterRegistrationBean<CorsFilter> corsFilter() {
//     CorsConfiguration config = new CorsConfiguration();
//     config.setAllowCredentials(true);
//     config.addAllowedOriginPattern("*");
//     config.addAllowedHeader("*");
//     config.addAllowedOrigin("http://localhost:3000");
//     config.addAllowedMethod("*");

//     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     source.registerCorsConfiguration("/**", config);

//     FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//     bean.setOrder(0);
//     return bean;
//   }
//      @Bean
//     public PasswordEncoder passwordEncoder() {
//         // NoOp, compares plain-text passwords
//         return NoOpPasswordEncoder.getInstance();
//     }
// }
