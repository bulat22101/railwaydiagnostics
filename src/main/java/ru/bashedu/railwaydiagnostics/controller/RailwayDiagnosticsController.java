package ru.bashedu.railwaydiagnostics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RailwayDiagnosticsController {

    @PostMapping("/leap")
    public String leap() {
        return "OK!";
    }

    @GetMapping("/start")
    public Double start() {
        return 1.0;
    }

    @GetMapping("/stop")
    public void stop() {
    }
}
