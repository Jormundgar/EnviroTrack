package com.volkov.envirotrackapi.controllers;

import com.volkov.envirotrackapi.dto.SensorDTO;
import com.volkov.envirotrackapi.models.Sensor;
import com.volkov.envirotrackapi.services.SensorService;
import com.volkov.envirotrackapi.util.SensorAndMeasurementErrorResponse;
import com.volkov.envirotrackapi.util.SensorAndMeasurementException;
import com.volkov.envirotrackapi.util.SensorValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.volkov.envirotrackapi.util.ErrorsMsgUtil.getBackErrors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @PostMapping("/sensors/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                     BindingResult bindingResult) {
        var sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if(bindingResult.hasErrors())
            getBackErrors(bindingResult);
        sensorService.save(sensor);
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

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
