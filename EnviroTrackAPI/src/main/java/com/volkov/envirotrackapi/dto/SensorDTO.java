package com.volkov.envirotrackapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDTO {

    @NotEmpty(message = "Sensor`s name should not be empty")
    @Size(min = 3, max = 30, message = "Sensor`s name should be between 3 and 30 characters")
    private String name;
}
