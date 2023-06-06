package com.volkov.envirotrackclient;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class ClientStarter {

    public static void main(String[] args) {
        System.out.println("Enter the name of sensor to register:");
        var scanner = new Scanner(System.in);
        var sensorName = scanner.nextLine();
        scanner.close();
        sensorRegistration(sensorName);
        var random = new Random();
        double maxTemp = 30.0;
        for (int i = 0; i < 500; i++){
            addMeasurements(random.nextDouble() * maxTemp, random.nextBoolean(), sensorName);
        }
    }

    public static void sensorRegistration(String sensorName) {
        final var url = "http://localhost:8080/api/sensors/registration";
        var jsonToSend = new HashMap<String, Object>();
        jsonToSend.put("name", sensorName);
        postRequest(url, jsonToSend);
    }

    public static void addMeasurements(Double temperature, Boolean isRaining, String sensorName) {
        final var url = "http://localhost:8080/api/measurements/add";
        var jsonToSend = new HashMap<String, Object>();
        jsonToSend.put("temperature", temperature);
        jsonToSend.put("raining", isRaining);
        jsonToSend.put("sensorOfMeasure", Map.of("name", sensorName));
        postRequest(url, jsonToSend);
    }

    public static void postRequest(String url, Map<String, Object> jsonToSend) {
        var restTemplate = new RestTemplate();
        var request = new HttpEntity<Object>(jsonToSend);
        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Request was send successfully!");
        } catch (HttpClientErrorException e) {
            System.out.println("Error!");
            System.out.println(e.getMessage());
        }
    }
}
