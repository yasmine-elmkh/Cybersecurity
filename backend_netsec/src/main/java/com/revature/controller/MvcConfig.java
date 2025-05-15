// package com.revature.controller;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class MvcConfig implements WebMvcConfigurer {

//     @Override
//     public void addViewControllers(ViewControllerRegistry registry) {
//         // Quand on va sur /admin ou /admin/… => on forwarde vers /admin/index.html
//         registry.addViewController("/admin").setViewName("forward:/admin/index.html");
//         registry.addViewController("/admin/").setViewName("forward:/admin/index.html");
//         // Et si tu utilises React Router, tu peux forwarder tout sous /admin/**
//         registry.addViewController("/admin/{spring:\\w+}")
//                 .setViewName("forward:/admin/index.html");
//         registry.addViewController("/admin/**/{spring:\\w+}")
//                 .setViewName("forward:/admin/index.html");
//     }
// }