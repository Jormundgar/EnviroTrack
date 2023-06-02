package com.volkov.envirotrackapi.services;

import com.volkov.envirotrackapi.models.Measurement;
import com.volkov.envirotrackapi.repositories.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        addMeasureTime(measurement);
        measurementRepository.save(measurement);
    }

    private void addMeasureTime(Measurement measurement) {
        measurement.setSensorOfMeasure(sensorService.findByName(measurement.getSensorOfMeasure().getName()).get());
        measurement.setMeasuredAt(new Date());
    }
}
