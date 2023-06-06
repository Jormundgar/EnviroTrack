package com.volkov.envirotrackclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MeasurementsResponse {
    private final List<MeasurementDTO> measurements;
}