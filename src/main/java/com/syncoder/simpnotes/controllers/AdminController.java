package com.syncoder.simpnotes.controllers;

import com.syncoder.simpnotes.domain.Role;
import com.syncoder.simpnotes.domain.User;
import com.syncoder.simpnotes.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/user/edit/{userId}")
    public String userEditForm(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/user/edit/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestParam(name = "username") String username, @RequestParam Map<String, String> form) {
        User user = userService.findUserById(userId);
        user.setUsername(username);
        userService.changeUserRole(user, form);
        return "redirect:/admin/users";
    }


}
