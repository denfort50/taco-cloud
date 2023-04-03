package ru.dkalchenko.tacocloud.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.dkalchenko.tacocloud.model.Person;
import ru.dkalchenko.tacocloud.model.TacoOrder;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    Optional<TacoOrder> findTacoOrderById(long id);

    List<TacoOrder> findByPersonOrderByPlacedAtDesc(Person person, Pageable pageable);
}
