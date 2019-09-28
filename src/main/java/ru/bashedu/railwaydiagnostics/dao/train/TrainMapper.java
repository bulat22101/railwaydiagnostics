package ru.bashedu.railwaydiagnostics.dao.train;

import ru.bashedu.railwaydiagnostics.dao.BaseObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TrainMapper implements BaseObjectMapper<Train> {
    @Override
    public Map<String, Object> mapObject(Train train) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", train.getId());
        map.put("coefficient", train.getCoefficient());
        return map;
    }

    @Override
    public Train mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Train.builder()
            .id(rs.getLong("id"))
            .coefficient(rs.getDouble("coefficient"))
            .build();
    }
}
