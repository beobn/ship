package com.example.shipnhanh.utills;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CallApiGogoleMap {
    public static void main(String[] args) throws IOException {
        String apiKey = "YOUR_GOOGLE_MAPS_API_KEY";
        String address = "1600 Amphitheatre Parkway, Mountain View, CA";

        // Xây dựng URL cho Geocoding API
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                address + "&key=" + apiKey;

        // Gửi yêu cầu và nhận phản hồi
        String jsonResponse = sendGetRequest(apiUrl);

        // Xử lý kết quả
        System.out.println(jsonResponse);
    }

    private static String sendGetRequest(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}
