package ru.bashedu.railwaydiagnostics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.bashedu.railwaydiagnostics.dao.user.WorkerDao;

@Configuration
public class MainConfig {
    private final JdbcTemplate jdbcTemplate;

    public MainConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    WorkerDao userDao(){
        return new WorkerDao(jdbcTemplate);
    }
}
