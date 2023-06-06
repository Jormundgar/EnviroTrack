package com.volkov.envirotrackclient;

import com.volkov.envirotrackclient.dto.MeasurementDTO;
import com.volkov.envirotrackclient.dto.MeasurementsResponse;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChartDrawing {
    private static List<Double> getTemperaturesFromDB() {
        final var restTemplate = new RestTemplate();
        final var url = "http://localhost:8080/api/measurements";
        var jsonResponse = restTemplate.getForObject(url, MeasurementsResponse.class);
        if (jsonResponse == null || jsonResponse.getMeasurements() == null)
            return Collections.emptyList();
        return jsonResponse
                .getMeasurements()
                .stream()
                .map(MeasurementDTO::getTemperature)
                .collect(Collectors.toList());
    }
}
