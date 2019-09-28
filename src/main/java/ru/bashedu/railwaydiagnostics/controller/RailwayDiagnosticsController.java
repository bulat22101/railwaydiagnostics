package ru.bashedu.railwaydiagnostics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bashedu.railwaydiagnostics.dto.ThresholdDto;
import ru.bashedu.railwaydiagnostics.service.MainService;

@RestController
@RequiredArgsConstructor
public class RailwayDiagnosticsController {
    private final MainService mainService;

    @PostMapping("/leap")
    public String leap() {
        return "OK!";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET, produces = "application/json")
    public ThresholdDto response(
        @RequestParam Long id,
        @RequestParam(value = "train_id") Long trainId,
        @RequestParam String device
    ) {
        System.err.println("/start");
        Long deviceId = mainService.getOrCreateDevice(device);
        return new ThresholdDto(mainService.startTrip(id, trainId, deviceId));
    }

    @GetMapping("/stop")
    public String stop() {
        return "OK!";
    }
}
