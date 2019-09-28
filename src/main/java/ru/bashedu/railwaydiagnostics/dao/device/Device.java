package ru.bashedu.railwaydiagnostics.dao.device;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Device {
    private Long id;
    private String deviceName;
    private Double coefficient;
}
