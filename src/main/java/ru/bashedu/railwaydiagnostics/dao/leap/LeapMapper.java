package ru.bashedu.railwaydiagnostics.dao.leap;

import ru.bashedu.railwaydiagnostics.dao.BaseObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class LeapMapper implements BaseObjectMapper<Leap> {
    @Override
    public Map<String, Object> mapObject(Leap leap) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", leap.getId());
        map.put("train_id", leap.getTrainId());
        map.put("worker_id", leap.getWorkerId());
        map.put("device_id", leap.getDeviceId());
        map.put("time", leap.getTime());
        map.put("latitude", leap.getLatitude());
        map.put("longitude", leap.getLongitude());
        map.put("acceleration", leap.getAcceleration());
        return map;
    }

    @Override
    public Leap mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Leap.builder()
            .id(rs.getLong("id"))
            .trainId(rs.getLong("train_id"))
            .workerId(rs.getLong("worker_id"))
            .deviceId(rs.getLong("device_id"))
            .time(rs.getLong("time"))
            .latitude(rs.getDouble("latitude"))
            .longitude(rs.getDouble("longitude"))
            .acceleration(rs.getDouble("acceleration"))
            .build();
    }
}
