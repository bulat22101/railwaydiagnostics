package ru.bashedu.railwaydiagnostics.service;

import lombok.RequiredArgsConstructor;
import ru.bashedu.railwaydiagnostics.dao.device.Device;
import ru.bashedu.railwaydiagnostics.dao.device.DeviceDao;
import ru.bashedu.railwaydiagnostics.dao.leap.Leap;
import ru.bashedu.railwaydiagnostics.dao.leap.LeapDao;
import ru.bashedu.railwaydiagnostics.dao.train.TrainDao;
import ru.bashedu.railwaydiagnostics.dao.worker.Worker;
import ru.bashedu.railwaydiagnostics.dao.worker.WorkerDao;
import ru.bashedu.railwaydiagnostics.dto.leap.LeapDto;
import ru.bashedu.railwaydiagnostics.dto.leap.LeapsDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SimpleMainService implements MainService {
    private static final double ACCELERATION_THRESHOLD = 5d;
    private final WorkerDao workerDao;
    private final DeviceDao deviceDao;
    private final TrainDao trainDao;
    private final LeapDao leapDao;

    @Override
    public Double startTrip(Long workerId, Long trainId, Long deviceId) {
        //TODO: Optimize DB queries
        Optional<Boolean> isInTrip = workerDao.isInTrip(workerId);
        if (!isInTrip.isPresent()) {
            throw new IllegalArgumentException("No such worker");
        }
        if (isInTrip.orElse(true)) {
            throw new IllegalArgumentException("Can't start trip, because worker trip status is " + isInTrip.orElse(null));
        }
        //workerDao.launchTrip(workerId, trainId, deviceId);
        Double trainCoefficient = trainDao.getCoefficient(trainId).orElse(1d);
        System.err.println(trainCoefficient);
        Double workerCoefficient = workerDao.getCoefficient(workerId).orElse(1d);
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

    @Override
    public void saveLeaps(LeapsDto leapsDto) {
        final Long workerId = leapsDto.getId();
        Optional<Boolean> isInTrip = workerDao.isInTrip(workerId);
        if (!isInTrip.isPresent()) {
            throw new IllegalArgumentException("No such worker");
        }
        if (isInTrip.orElse(true)) {
            throw new IllegalArgumentException("Can't log, because worker trip status is " + isInTrip.orElse(null));
        }
        List<Leap> list = leapsDto.getLeaps().stream()
            .map(e -> convertToLeap(e, workerId)).collect(Collectors.toList());
        leapDao.appendAll(list);
    }


    private Leap convertToLeap(LeapDto leapDto, Long workerId) {
        Optional<Worker> worker = workerDao.getWorker(workerId);
        return Leap.builder()
            .workerId(workerId)
            .deviceId(worker.map(Worker::getDeviceId).orElse(null))
            .trainId(worker.map(Worker::getTrainId).orElse(null))
            .time(leapDto.getCreation())
            .location(leapDto.getLocation())
            .acceleration(leapDto.getAcceleration())
            .build();
    }
}
