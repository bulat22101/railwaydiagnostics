package ru.bashedu.railwaydiagnostics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.bashedu.railwaydiagnostics.dao.device.DeviceDao;
import ru.bashedu.railwaydiagnostics.dao.leap.LeapDao;
import ru.bashedu.railwaydiagnostics.dao.train.TrainDao;
import ru.bashedu.railwaydiagnostics.dao.worker.WorkerDao;
import ru.bashedu.railwaydiagnostics.service.SimpleMainService;

@Configuration
public class MainConfig {
    private final JdbcTemplate jdbcTemplate;

    public MainConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    WorkerDao workerDao() {
        return new WorkerDao(jdbcTemplate);
    }

    @Bean
    LeapDao leapDao() {
        return new LeapDao(jdbcTemplate);
    }

    @Bean
    TrainDao trainDao() {
        return new TrainDao(jdbcTemplate);
    }

    @Bean
    DeviceDao deviceDao() {
        return new DeviceDao(jdbcTemplate);
    }

    @Bean
    SimpleMainService simpleMainService(
        WorkerDao workerDao,
        DeviceDao deviceDao,
        TrainDao trainDao,
        LeapDao leapDao
    ) {
        return new SimpleMainService(workerDao, deviceDao, trainDao, leapDao);
    }
}
