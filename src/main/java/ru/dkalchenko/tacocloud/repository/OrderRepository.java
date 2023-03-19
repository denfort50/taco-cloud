package ru.dkalchenko.tacocloud.repository;

import ru.dkalchenko.tacocloud.model.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
