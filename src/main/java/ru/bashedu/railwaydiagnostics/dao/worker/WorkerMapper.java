package ru.bashedu.railwaydiagnostics.dao.worker;

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
        map.put("device_id", worker.getDeviceId());
        map.put("coefficient", worker.getCoefficient());
        return map;
    }

    @Override
    public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Worker.builder()
            .id(rs.getLong("id"))
            .isInTrip(rs.getBoolean("is_in_trip"))
            .trainId(rs.getLong("train_id"))
            .deviceId(rs.getLong("device_id"))
            .coefficient(rs.getDouble("coefficient"))
            .build();
    }
}
