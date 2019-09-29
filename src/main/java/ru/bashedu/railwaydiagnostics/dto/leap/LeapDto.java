package ru.bashedu.railwaydiagnostics.dto.leap;

import lombok.Data;

@Data
public class LeapDto {
    private Long creation;
    private double acceleration;
    private Double longitude;
    private Double latitude;
}
