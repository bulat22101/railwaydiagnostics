package ru.bashedu.railwaydiagnostics.service;

import lombok.RequiredArgsConstructor;
import ru.bashedu.railwaydiagnostics.dao.device.Device;
import ru.bashedu.railwaydiagnostics.dao.device.DeviceDao;
import ru.bashedu.railwaydiagnostics.dao.train.TrainDao;
import ru.bashedu.railwaydiagnostics.dao.worker.Worker;
import ru.bashedu.railwaydiagnostics.dao.worker.WorkerDao;

import java.util.Optional;

@RequiredArgsConstructor
public class SimpleMainService implements MainService {
    private static final double ACCELERATION_THRESHOLD = 5d;
    private final WorkerDao workerDao;
    private final DeviceDao deviceDao;
    private final TrainDao trainDao;

    @Override
    public Double startTrip(Long workerId,Long trainId, Long deviceId) {
        Optional<Boolean>isInTrip = workerDao.isInTrip(workerId);
        if (!isInTrip.isPresent()) {
            throw new IllegalArgumentException("No such worker");
        }
        if (isInTrip.orElse(true)) {
            throw new IllegalArgumentException("Can't start trip, because worker trip status is " + isInTrip.orElse(null));
        }
        Double trainCoefficient = trainDao.getCoefficient(trainId).orElse(1d);
        System.err.println(trainCoefficient);
        Double workerCoefficient =workerDao.getCoefficient(workerId).orElse(1d);
        System.err.println(workerCoefficient);
        Double deviceCoefficient = deviceDao.getCoefficient(deviceId).orElse(1d);
        System.err.println(deviceCoefficient);
        return trainCoefficient * workerCoefficient * deviceCoefficient * ACCELERATION_THRESHOLD;
    }

    @Override
    public Long getOrCreateDevice(String deviceName) {
        Optional<Long> deviceId = deviceDao.getId(deviceName);
        if (!deviceId.isPresent()) {
            deviceId = Optional.ofNullable(deviceDao.appendDevice(deviceName)).map(Device::getId);
        }
        return deviceId.orElse(null);
    }
}
