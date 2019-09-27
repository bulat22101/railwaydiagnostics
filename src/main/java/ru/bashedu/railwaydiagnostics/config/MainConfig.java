package ru.bashedu.railwaydiagnostics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.bashedu.railwaydiagnostics.user.WorkerDao;

@Configuration
public class MainConfig {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MainConfig(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    WorkerDao userDao(){
        return new WorkerDao(jdbcTemplate);
    }
}
