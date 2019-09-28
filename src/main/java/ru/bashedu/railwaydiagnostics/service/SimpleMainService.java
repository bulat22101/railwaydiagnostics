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
    public Double startTrip(Long workerId, Long deviceId, Long trainId) {
        Optional<Worker> worker = workerDao.getWorker(workerId);
        if (!worker.isPresent()) {
            throw new IllegalArgumentException("No such worker");
        }
        Optional<Boolean> isInTrip = worker.map(Worker::getIsInTrip);
        if (isInTrip.orElse(true)) {
            throw new IllegalArgumentException("Can't start trip, because worker trip status is " + isInTrip.orElse(null));
        }
        Double trainCoefficient = trainDao.getCoefficient(trainId).orElse(1d);
        Double workerCoefficient = worker.map(Worker::getCoefficient).orElse(1d);
        Double deviceCoefficient = deviceDao.getCoefficient(deviceId).orElse(1d);
        return trainCoefficient * workerCoefficient * deviceCoefficient * ACCELERATION_THRESHOLD;
    }

    @Override
    public Long getOrCreateDevice(String deviceName) {
        Optional<Long> deviceId = deviceDao.getId(deviceName);
        if (!deviceId.isPresent()) {
            Device device = Device.builder()
                .deviceName(deviceName)
                .coefficient(1d)
                .build();
            deviceId = Optional.ofNullable(deviceDao.save(device)).map(Device::getId);
        }
        return deviceId.orElse(null);
    }
}
