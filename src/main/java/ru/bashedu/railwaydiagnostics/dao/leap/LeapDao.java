package ru.bashedu.railwaydiagnostics.dao.leap;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.bashedu.railwaydiagnostics.dao.BaseDao;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LeapDao extends BaseDao<Leap> {
    public LeapDao(JdbcTemplate jdbcTemplate) {
        super("leaps", jdbcTemplate, new LeapMapper());
    }

    public void appendAll(Collection<Leap> leaps) {
        if (leaps == null || leaps.isEmpty()) {
            return;
        }
        List<Object[]> params = leaps.stream()
            .map(leap -> new Object[]{
                leap.getWorkerId(),
                leap.getTrainId(),
                leap.getDeviceId(),
                leap.getTime(),
                leap.getLatitude(),
                leap.getLongitude(),
                leap.getAcceleration()
            })
            .collect(Collectors.toList());
        jdbcTemplate.batchUpdate(
            String.format(
                "INSERT INTO %s (id, worker_id, train_id, device_id, time, latitude, longitude, acceleration) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)",
                tableName
            ),
            params
        );
    }
}
