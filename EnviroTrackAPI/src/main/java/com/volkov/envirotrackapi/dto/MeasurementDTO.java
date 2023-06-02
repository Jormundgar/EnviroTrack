package com.volkov.envirotrackapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MeasurementDTO {

    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    @NotNull(message = "Value should not be empty")
    private Double temperature;

    @NotNull(message = "Raining should not be empty")
    private Boolean raining;

    @NotNull(message = "The measure have to made by any sensor")
    private SensorDTO sensorOfMeasure;
}
