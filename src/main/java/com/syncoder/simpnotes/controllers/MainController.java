package com.syncoder.simpnotes.controllers;

import com.syncoder.simpnotes.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping
    public String mainPage(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "main";
    }
}
