package ru.dkalchenko.tacocloud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dkalchenko.tacocloud.model.Ingredient;
import ru.dkalchenko.tacocloud.repository.IngredientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
}
