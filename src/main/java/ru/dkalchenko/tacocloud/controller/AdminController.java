package ru.dkalchenko.tacocloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dkalchenko.tacocloud.service.OrderService;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final OrderService orderService;

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        orderService.deleteAllOrders();
        return "redirect:/admin";
    }
}
