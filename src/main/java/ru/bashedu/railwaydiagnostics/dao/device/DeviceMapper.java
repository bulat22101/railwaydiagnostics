package ru.bashedu.railwaydiagnostics.dao.device;

import ru.bashedu.railwaydiagnostics.dao.BaseObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DeviceMapper implements BaseObjectMapper<Device> {
    @Override
    public Map<String, Object> mapObject(Device device) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", device.getId());
        map.put("device_name", device.getDeviceName());
        map.put("coefficient", device.getCoefficient());
        return map;
    }

    @Override
    public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Device.builder()
            .id(rs.getLong("id"))
            .deviceName(rs.getString("device_name"))
            .coefficient(rs.getDouble("coefficient"))
            .build();
    }
}
