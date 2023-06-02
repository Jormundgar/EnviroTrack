package com.volkov.envirotrackapi.controllers;

import com.volkov.envirotrackapi.dto.MeasurementDTO;
import com.volkov.envirotrackapi.dto.MeasurementsToResponse;
import com.volkov.envirotrackapi.models.Measurement;
import com.volkov.envirotrackapi.services.MeasurementService;
import com.volkov.envirotrackapi.util.MeasurementValidator;
import com.volkov.envirotrackapi.util.SensorAndMeasurementErrorResponse;
import com.volkov.envirotrackapi.util.SensorAndMeasurementException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.volkov.envirotrackapi.util.ErrorsMsgUtil.getBackErrors;

@RestController
@RequestMapping("/api/measurements")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MeasurementController {

    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;
    private final MeasurementService measurementService;

    @GetMapping()
    public MeasurementsToResponse getAll() {
        return new MeasurementsToResponse(measurementService
                .findAll()
                .stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Long getAllRainyDays() {
        return measurementService
                .findAll()
                .stream()
                .filter(Measurement::getRaining)
                .count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasure(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        var measurement = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);
        if(bindingResult.hasErrors())
            getBackErrors(bindingResult);
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorAndMeasurementErrorResponse> handleException(SensorAndMeasurementException e) {
        var response = new SensorAndMeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
