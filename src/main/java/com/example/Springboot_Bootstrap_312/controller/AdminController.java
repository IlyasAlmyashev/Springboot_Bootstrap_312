package com.example.Springboot_Bootstrap_312.controller;

import com.example.Springboot_Bootstrap_312.model.Role;
import com.example.Springboot_Bootstrap_312.model.User;
import com.example.Springboot_Bootstrap_312.service.RoleService;
import com.example.Springboot_Bootstrap_312.service.UserService;
import com.example.Springboot_Bootstrap_312.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String findAll(@AuthenticationPrincipal User user,
                          Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("allRoles", roleService.findAll());
        return "admin";
    }

    @PostMapping("/add-user")
    public String createUser(@ModelAttribute User user
//                             @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles
    ) {
//        Set<Role> roleSet = new HashSet<>();
//        for (String role : checkBoxRoles) {
//            roleSet.add(roleService.getRoleByName(role));
//        }
//        user.setRoles(roleSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") Long id
//                             @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles
    ) {
//        Set<Role> roleSet = new HashSet<>();
//        for (String role : checkBoxRoles) {
//            roleSet.add(roleService.getRoleByName(role));
//        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}