package ru.bashedu.railwaydiagnostics.service;

import ru.bashedu.railwaydiagnostics.dto.leap.LeapsDto;

public interface MainService {
    Double startTrip(Long workerId, Long trainId, Long deviceId);
    Long getOrCreateDevice(String device);
    void saveLeaps(LeapsDto leapsDto);
}
