package ru.bashedu.railwaydiagnostics.dao.user;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.bashedu.railwaydiagnostics.dao.BaseDao;


import java.util.Optional;

public class WorkerDao extends BaseDao<Worker> {

    public WorkerDao(JdbcTemplate jdbcTemplate) {
        super("workers", jdbcTemplate, new WorkerMapper());
    }

    public Optional<Boolean> isInTrip(Long id) {
        return jdbcTemplate.queryForList(String.format("SELECT is_in_trip FROM %s WHERE id = ?", tableName), Boolean.class, id)
            .stream().findFirst();
    }

    public void launchTrip(Long id, Long trainId, String device) {
        jdbcTemplate.update(
            String.format("UPDATE %s SET is_in_trip=true, device=?, train_id=? WHERE id=?", tableName),
            device, trainId, id
        );
    }
}
