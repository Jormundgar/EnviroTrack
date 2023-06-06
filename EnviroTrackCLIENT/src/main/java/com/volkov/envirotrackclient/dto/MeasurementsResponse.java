package com.volkov.envirotrackclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
public class MeasurementsResponse {
    private final List<MeasurementDTO> measurements;
}