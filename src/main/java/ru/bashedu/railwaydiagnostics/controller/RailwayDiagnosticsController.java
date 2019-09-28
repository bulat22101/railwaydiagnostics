package ru.bashedu.railwaydiagnostics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RailwayDiagnosticsController {

    @GetMapping("/leap")
    public void leap() {
    }

    @GetMapping("/start")
    public Double start(@RequestParam Long id, @RequestParam(value = "train") Long trainId, @RequestParam String device) {
        return 1.0;
    }

    @GetMapping("/stop")
    public void stop() {
    }
}
