package com.example.shipnhanh.utills;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
public class DistanceCalculator {
        public static void  getNumberKm(Double longitude ,Double latitude)  {
            try {
                String origin = "Hanoi,%20Vietnam";
                String destination = "Ho%20Chi%20Minh%20City, Vietnam"; // Mã hóa dấu cách
                String apiKey = "AIzaSyCN7wkQpBMR6nIE0pZrLkndpnvJCDm-8ps";

                String apiUrl = "https://maps.googleapis.com/maps/api/distancematrix/json"
                        + "?origins=" + origin.replaceAll(" ", "%20")
                        + "&destinations=" + destination
                        + "&key=" + apiKey;

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(apiUrl))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    String responseData = response.body();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < rows.length(); i++) {
                        JSONObject row = rows.getJSONObject(i);
                        JSONArray elements = row.getJSONArray("elements");
                        for (int j = 0; j < elements.length(); j++) {
                            JSONObject element = elements.getJSONObject(j);
                            JSONObject location = element.getJSONObject("location");
                            longitude = location.getDouble("lat");
                            latitude = location.getDouble("lng");
                            System.out.println("Latitude: " + latitude);
                            System.out.println("Longitude: " + longitude);

                        }
                    }
                } else {
                    System.err.println("Error: " + response.statusCode());
                }

            } catch (IOException | InterruptedException | URISyntaxException e) {
                e.printStackTrace();
            }
        }

}
