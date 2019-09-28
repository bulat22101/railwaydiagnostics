package ru.bashedu.railwaydiagnostics.dao.worker;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.bashedu.railwaydiagnostics.dao.BaseDao;


import java.util.Objects;
import java.util.Optional;

public class WorkerDao extends BaseDao<Worker> {

    public WorkerDao(JdbcTemplate jdbcTemplate) {
        super("workers", jdbcTemplate, new WorkerMapper());
    }

    public Optional<Boolean> isInTrip(Long id) {
        return jdbcTemplate.queryForList(String.format("SELECT is_in_trip FROM %s WHERE id = ?", tableName), Boolean.class, id)
            .stream().filter(Objects::nonNull).findFirst();
    }

    public void launchTrip(Long id, Long trainId, Long device) {
        jdbcTemplate.update(
            String.format("UPDATE %s SET is_in_trip=true, device_id=?, train_id=? WHERE id=?", tableName),
            device, trainId, id
        );
    }

    public Optional<Double> getCoefficient(Long id) {
        return jdbcTemplate.queryForList(
            String.format("SELECT coefficient FROM %s WHERE id = ?", tableName),
            Double.class,
            id).stream().filter(Objects::nonNull).findFirst();
    }

    public Optional<Worker> getWorker(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
            String.format("SELECT * FROM %s WHERE id=?", tableName),
            objectMapper, id));
    }
}
