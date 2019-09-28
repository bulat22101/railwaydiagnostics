package ru.bashedu.railwaydiagnostics.service;

import java.util.Optional;

public interface MainService {
    Double startTrip(Long workerId, Long trainId, Long deviceId);
    Long getOrCreateDevice(String device);
}
