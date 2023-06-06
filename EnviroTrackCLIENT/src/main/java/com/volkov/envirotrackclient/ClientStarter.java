package com.volkov.envirotrackclient;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class ClientStarter {

    public static void main(String[] args) {
        System.out.println("Enter the name of sensor to register:");
        var scanner = new Scanner(System.in);
        var nameOfSensor = scanner.nextLine();
        scanner.close();
        sensorRegistration(nameOfSensor);
    }

    public static void sensorRegistration(String sensorName) {
        var restTemplate = new RestTemplate();
        var jsonToSend = new HashMap<String, Object>();
        jsonToSend.put("name", sensorName);
        var request = new HttpEntity<Object>(jsonToSend);
        var url = "http://localhost:8080/api/sensors/registration";
        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Sensor registration: Done!");
        } catch (HttpClientErrorException e) {
            System.out.println("Error!");
            System.out.println(e.getMessage());
        }
    }
}
