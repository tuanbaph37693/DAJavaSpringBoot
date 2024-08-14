package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @RequestMapping("security/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập");
        return "security/login";
    }

    @RequestMapping("security/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Đăng nhập thành công");
        return "security/login";
    }

    @RequestMapping("security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Đăng nhập không thành công");
        return "security/login";
    }

    @RequestMapping("security/login/unauthorized")
    public String unauthorized(Model model) {
        model.addAttribute("message", "Không có quyền truy xuất");
        return "security/login";
    }

    @RequestMapping("security/logout/success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất");
        return "security/login";
    }


}
