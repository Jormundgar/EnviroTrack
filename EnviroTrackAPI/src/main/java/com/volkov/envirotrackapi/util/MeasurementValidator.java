package com.volkov.envirotrackapi.util;

import com.volkov.envirotrackapi.models.Measurement;
import com.volkov.envirotrackapi.services.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> cClass) {
        return Measurement.class.equals(cClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var measurement = (Measurement) target;
        if(sensorService.findByName(measurement.getSensorOfMeasure().getName()).isEmpty()) {
            errors.rejectValue("sensorOfMeasure", "", "This sensor not exist");
        }
    }
}
