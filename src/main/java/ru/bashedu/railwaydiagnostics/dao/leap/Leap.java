package ru.bashedu.railwaydiagnostics.dao.leap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Leap {
    private Long id;
    private Long trainId;
    private Long workerId;
    private Long deviceId;
    private Long time;
    private Double latitude;
    private Double longitude;
    private Double acceleration;
}
