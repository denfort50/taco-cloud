package ru.dkalchenko.tacocloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dkalchenko.tacocloud.model.Ingredient;
import ru.dkalchenko.tacocloud.service.IngredientService;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
public class IngredientController {

    private final IngredientService service;

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return service.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId) {
        service.deleteById(ingredientId);
    }
}
