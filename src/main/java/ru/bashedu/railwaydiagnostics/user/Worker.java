package ru.bashedu.railwaydiagnostics.user;

import lombok.Data;

@Data
public class Worker {
    private Long id;
    private String name;
    private Boolean inTravel;
    private Long trainId;
    private String device;
}
