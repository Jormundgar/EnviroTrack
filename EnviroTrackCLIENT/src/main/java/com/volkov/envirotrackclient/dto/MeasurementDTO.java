package com.volkov.envirotrackclient.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MeasurementDTO {

    private Double temperature;
    private Boolean raining;
    private SensorDTO sensorOfMeasure;
}

