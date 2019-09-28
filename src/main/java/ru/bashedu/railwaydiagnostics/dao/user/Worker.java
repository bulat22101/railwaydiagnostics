package ru.bashedu.railwaydiagnostics.dao.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Worker {
    private Long id;
    private Boolean isInTrip;
    private Long trainId;
    private String device;
    private Double coefficient;
}
