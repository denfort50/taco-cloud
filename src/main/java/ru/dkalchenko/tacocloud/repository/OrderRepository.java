package ru.dkalchenko.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dkalchenko.tacocloud.model.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
