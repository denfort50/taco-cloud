package ru.dkalchenko.tacocloud.repository;

import ru.dkalchenko.tacocloud.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {

    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
