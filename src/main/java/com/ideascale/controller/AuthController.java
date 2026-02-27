package com.ideascale.controller;

import com.ideascale.data.RegisterData;
import com.ideascale.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerData", new RegisterData());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("registerData") RegisterData registerData,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!registerData.getPassword().equals(registerData.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.registerData", "Passwords do not match");
        }

        if (userService.existsByUsername(registerData.getUsername())) {
            bindingResult.rejectValue("username", "error.registerData", "Username is already taken");
        }

        if (userService.existsByEmail(registerData.getEmail())) {
            bindingResult.rejectValue("email", "error.registerData", "Email is already registered");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerUser(registerData);

        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
        return "redirect:/login";
    }
}
