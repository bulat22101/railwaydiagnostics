package ru.bashedu.railwaydiagnostics.service;

import java.util.Optional;

public interface MainService {
    Optional<Double> startTrip(Long workerId, Long deviceId, Long trainId);
    Optional<Long> getOrCreateDevice(String device);
    Optional<Boolean> isInTrip(Long workerId);
}
