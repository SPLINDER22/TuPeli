package com.urnovie.tupeli.controller;

import com.urnovie.tupeli.entity.User;
import com.urnovie.tupeli.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String login() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "redirect:/movies"; // Vista después del login
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "/register"; // muestra la vista del formulario
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String username,
                                  @RequestParam String email,
                                  @RequestParam String password) {

        // Verificar si ya existe el email
        if (userRepository.findByEmail(email).isPresent()) {
            return "redirect:/register?error";
        }

        // Crear nuevo usuario
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        // Redirigir al login (index)
        return "redirect:/?registerSuccess";
    }


}
