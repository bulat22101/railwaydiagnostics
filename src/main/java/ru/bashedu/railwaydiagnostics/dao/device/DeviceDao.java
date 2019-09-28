package ru.bashedu.railwaydiagnostics.dao.device;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.bashedu.railwaydiagnostics.dao.BaseDao;

import java.util.Objects;
import java.util.Optional;

public class DeviceDao extends BaseDao<Device> {
    public DeviceDao(JdbcTemplate jdbcTemplate){
        super("devices", jdbcTemplate, new DeviceMapper());
    }

    public Optional<Double> getCoefficient(Long id){
        return jdbcTemplate.queryForList(
            String.format("SELECT coefficient FROM %s WHERE id = ?", tableName),
            Double.class,
            id).stream().filter(Objects::nonNull).findFirst();
    }

    public Optional<Long> getId(String deviceName){
        return jdbcTemplate.queryForList(
            String.format("SELECT id FROM %s WHERE device_name = ?", tableName),
            Long.class,
            deviceName).stream().findFirst();
    }
}
