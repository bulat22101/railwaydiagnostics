package ru.bashedu.railwaydiagnostics.dao.worker;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Worker {
    private Long id;
    private Boolean isInTrip;
    private Long trainId;
    private Long deviceId;
    private Double coefficient;
}
