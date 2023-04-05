package ru.dkalchenko.tacocloud.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.dkalchenko.tacocloud.model.Person;
import ru.dkalchenko.tacocloud.model.TacoOrder;
import ru.dkalchenko.tacocloud.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    @PostAuthorize("hasRole('ADMIN') || returnObject.person.username == authentication.name")
    public TacoOrder getOrder(long id) {
        return findTacoOrderById(id).orElseThrow();
    }

    public TacoOrder save(TacoOrder tacoOrder) {
        return orderRepository.save(tacoOrder);
    }

    public Optional<TacoOrder> findTacoOrderById(long id) {
        return orderRepository.findTacoOrderById(id);
    }

    public List<TacoOrder> findByPersonOrderByPlacedAtDesc(Person person, Pageable pageable) {
        return orderRepository.findByPersonOrderByPlacedAtDesc(person, pageable);
    }

    public Optional<TacoOrder> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
