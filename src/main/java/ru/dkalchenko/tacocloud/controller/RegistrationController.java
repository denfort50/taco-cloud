package ru.dkalchenko.tacocloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dkalchenko.tacocloud.model.RegistrationForm;
import ru.dkalchenko.tacocloud.repository.PersonRepository;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        personRepository.save(form.toPerson(passwordEncoder));
        return "redirect:/login";
    }
}
