package ru.bashedu.railwaydiagnostics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bashedu.railwaydiagnostics.dao.user.WorkerDao;

import java.util.Optional;

@RestController
public class RailwayDiagnosticsController {
    private final WorkerDao workerDao;

    public RailwayDiagnosticsController(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    @GetMapping("/leap")
    public void leap() {
    }

    @GetMapping("/start")
    public Double start(@RequestParam Long id, @RequestParam(value = "train") Long trainId, @RequestParam String device) {
        Optional<Boolean> isInTrip = workerDao.isInTrip(id);
        if(!isInTrip.isPresent()){
            throw new IllegalArgumentException("No such worker");
        }
        if(isInTrip.get()){
            throw new IllegalArgumentException("Previous trip is not ended yet");
        }
        workerDao.launchTrip(id, trainId, device);
        return 1.0;
    }

    @GetMapping("/stop")
    public void stop() {
    }
}
