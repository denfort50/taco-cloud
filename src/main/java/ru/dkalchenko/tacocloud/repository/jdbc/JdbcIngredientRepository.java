package ru.dkalchenko.tacocloud.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dkalchenko.tacocloud.model.Ingredient;
import ru.dkalchenko.tacocloud.repository.IngredientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL = "select id, name, type from Ingredient";
    private static final String FIND_BY_ID = "select id, name, type from Ingredient where id = ?";
    private static final String INSERT = "insert into Ingredient (id, name, type) values (?, ?, ?)";

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type")));
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query(FIND_ALL, this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query(FIND_BY_ID, this::mapRowToIngredient, id);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(INSERT, ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
        return ingredient;
    }
}
