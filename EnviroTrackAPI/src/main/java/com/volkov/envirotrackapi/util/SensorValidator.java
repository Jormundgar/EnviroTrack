package com.volkov.envirotrackapi.util;

import com.volkov.envirotrackapi.models.Sensor;
import com.volkov.envirotrackapi.services.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> cClass) {
        return Sensor.class.equals(cClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var sensor = (Sensor) target;
        if(sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "This sensor already exist");
        }
    }
}
