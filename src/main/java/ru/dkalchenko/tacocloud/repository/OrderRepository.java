package ru.dkalchenko.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dkalchenko.tacocloud.model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
