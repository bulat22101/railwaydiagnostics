package ru.bashedu.railwaydiagnostics.user;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class WorkerDao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Optional<Boolean> isInTrip(Long id) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return jdbcTemplate.queryForList("SELECT is_in_trip FROM workers WHERE id = :id", namedParameters, Boolean.class)
            .stream().findFirst();
    }
    public void launchTrip(Long id, Long trainId, String device){
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("device", device);
        namedParameters.put("trainId", trainId);
        jdbcTemplate.update("UPDATE workers SET is_in_trip=true, device=:device, train_id=:trainId", namedParameters);
    }
}
