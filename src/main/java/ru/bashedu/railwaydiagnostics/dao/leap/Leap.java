package ru.bashedu.railwaydiagnostics.dao.leap;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Leap {
    private Long id;
    private Long trainId;
    private Long workerId;
    private Long deviceId;
    private Instant time;
    private String location;
    private Double acceleration;
}
