package ru.dkalchenko.tacocloud.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.dkalchenko.tacocloud.model.Taco;
import ru.dkalchenko.tacocloud.repository.TacoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TacoService {

    private final TacoRepository tacoRepository;

    public Taco save(Taco taco) {
        return tacoRepository.save(taco);
    }

    public List<Taco> findAll(PageRequest page) {
        return tacoRepository.findAll(page).getContent();
    }

    public Optional<Taco> findById(long id) {
        return tacoRepository.findById(id);
    }
}
