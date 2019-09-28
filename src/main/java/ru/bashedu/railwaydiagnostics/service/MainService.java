package ru.bashedu.railwaydiagnostics.service;

import java.util.Optional;

public interface MainService {
    Double startTrip(Long workerId, Long deviceId, Long trainId);
    Long getOrCreateDevice(String device);
}
