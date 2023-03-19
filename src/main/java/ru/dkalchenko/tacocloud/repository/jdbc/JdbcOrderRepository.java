package ru.dkalchenko.tacocloud.repository.jdbc;

import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dkalchenko.tacocloud.model.IngredientRef;
import ru.dkalchenko.tacocloud.model.Taco;
import ru.dkalchenko.tacocloud.model.TacoOrder;
import ru.dkalchenko.tacocloud.repository.OrderRepository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_TACO_ORDER = """
            insert into Taco_Order (delivery_name, delivery_street, delivery_city, delivery_state, 
            delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) values (?,?,?,?,?,?,?,?,?)
            """;
    private static final String INSERT_TACO = """
            insert into Taco (name, created_at, taco_order, taco_order_key) values (?, ?, ?, ?)
            """;
    private static final String INSERT_INGREDIENT_REF = """
            insert into Ingredient_Ref (ingredient, taco, taco_key) values (?, ?, ?)
            """;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientRefs) {
        int key = 0;
        for (IngredientRef ingredientRef : ingredientRefs) {
            jdbcTemplate.update(INSERT_INGREDIENT_REF, ingredientRef.getIngredient(), tacoId, key++);
        }
    }

    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory preparedStatementCreatorFactory =
                new PreparedStatementCreatorFactory(INSERT_TACO, Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG);
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator preparedStatementCreator =
                preparedStatementCreatorFactory.newPreparedStatementCreator(
                        Arrays.asList(taco.getName(), taco.getCreatedAt(), orderId, orderKey));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);
        List<IngredientRef> ingredientRefs = taco.getIngredients().stream()
                .map(ingredient -> new IngredientRef(ingredient.getId()))
                .collect(Collectors.toList());
        saveIngredientRefs(tacoId, ingredientRefs);
        return tacoId;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory preparedStatementCreatorFactory =
                new PreparedStatementCreatorFactory(INSERT_TACO_ORDER,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        order.setPlacedAt(new Date());
        PreparedStatementCreator preparedStatementCreator =
                preparedStatementCreatorFactory.newPreparedStatementCreator(
                        Arrays.asList(
                                order.getDeliveryName(),
                                order.getDeliveryStreet(),
                                order.getDeliveryCity(),
                                order.getDeliveryState(),
                                order.getDeliveryZip(),
                                order.getCcNumber(),
                                order.getCcExpiration(),
                                order.getCcCVV(),
                                order.getPlacedAt()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }
        return order;
    }
}
