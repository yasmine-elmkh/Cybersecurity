package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {

    @GetMapping({ "/admin", "/admin/**" })
    public String forwardAdmin() {
        return "forward:/admin/index.html";
    }
}
