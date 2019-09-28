package ru.bashedu.railwaydiagnostics.dao.user;

import ru.bashedu.railwaydiagnostics.dao.BaseObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WorkerMapper implements BaseObjectMapper<Worker> {
    @Override
    public Map<String, Object> mapObject(Worker worker) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", worker.getId());
        map.put("is_in_trip", worker.getIsInTrip());
        map.put("train_id", worker.getTrainId());
        map.put("device", worker.getDevice());
        map.put("coefficient", worker.getCoefficient());
        return map;
    }

    @Override
    public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Worker.builder()
            .id(rs.getLong("id"))
            .isInTrip(rs.getBoolean("is_in_trip"))
            .trainId(rs.getLong("train_id"))
            .device(rs.getString("device"))
            .coefficient(rs.getDouble("coefficient"))
            .build();
    }
}
