package com.volkov.envirotrackclient;

import com.volkov.envirotrackclient.dto.MeasurementDTO;
import com.volkov.envirotrackclient.dto.MeasurementsResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChartDrawing {
    public static void main(String[] args) {
        var temperatures = getTemperaturesFromDB();
        drawingChart(temperatures);
    }

    private static void drawingChart(List<Double> temperatures) {
        double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yData = temperatures.stream().mapToDouble(temp -> temp).toArray();

        XYChart chart = QuickChart
                .getChart("Temperatures", "Measurements", "Temperature", "y(x)",
                        xData, yData);
        new SwingWrapper(chart).displayChart();
    }

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
