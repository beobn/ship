package com.example.shipnhanh.testapi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CallApiGogoleTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apiKey = "AIzaSyCN7wkQpBMR6nIE0pZrLkndpnvJCDm-8ps";
        String latitude = "37.7749"; // Latitude
        String longitude = "-122.4194"; // Longitude

        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?" +
                "latlng=" + latitude + "," + longitude +
                "&key=" + apiKey;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
