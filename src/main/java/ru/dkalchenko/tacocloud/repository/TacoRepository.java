package ru.dkalchenko.tacocloud.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.dkalchenko.tacocloud.model.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
