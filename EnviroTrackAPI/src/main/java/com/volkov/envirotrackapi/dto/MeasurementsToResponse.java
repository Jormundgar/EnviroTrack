package com.volkov.envirotrackapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MeasurementsToResponse {
    private final List<MeasurementDTO> measurements;
}
