package ru.bashedu.railwaydiagnostics.dto.leap;

import lombok.Data;

import java.time.Instant;

@Data
public class LeapDto {
    private Instant creation;
    private double acceleration;
    private String location;
}
