package ru.bashedu.railwaydiagnostics.dao.leap;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.bashedu.railwaydiagnostics.dao.BaseDao;

public class LeapDao extends BaseDao<Leap> {
    public LeapDao(JdbcTemplate jdbcTemplate){
        super("leaps", jdbcTemplate, new LeapMapper());
    }
}
