package ru.dkalchenko.tacocloud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dkalchenko.tacocloud.model.TacoOrder;
import ru.dkalchenko.tacocloud.repository.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(TacoOrder tacoOrder) {
        orderRepository.save(tacoOrder);
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
