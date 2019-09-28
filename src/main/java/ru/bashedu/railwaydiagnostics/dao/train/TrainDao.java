package ru.bashedu.railwaydiagnostics.dao.train;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.bashedu.railwaydiagnostics.dao.BaseDao;

import java.util.Objects;
import java.util.Optional;

public class TrainDao extends BaseDao<Train> {

    public TrainDao(JdbcTemplate jdbcTemplate){
        super("trains", jdbcTemplate, new TrainMapper());
    }

    public Optional<Double> getCoefficient(Long id){
        return jdbcTemplate.queryForList(
            String.format("SELECT coefficient FROM %s WHERE id = ?", tableName),
            Double.class,
            id).stream().filter(Objects::nonNull).findFirst();
    }
}
