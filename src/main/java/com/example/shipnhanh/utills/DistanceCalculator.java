package com.example.shipnhanh.utills;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;

import org.json.JSONArray;
import org.json.JSONObject;
public class DistanceCalculator {

    // Định nghĩa thuộc tính cố định
    private final static int BASE_RATE = 13000;
    private final static int ADDITIONAL_RATE = 3000;
    private final static double NIGHT_EXTRA_FEE = 2;
    private final static double RAIN_EXTRA_FEE = 3;
    private final static int EXTRA_PER_ITEM = 1000;
    private final static double DOOR_DELIVERY_EXTRA = 3000;

    public static void main(String[] args) {
        getNumberKm (20.203,95.304);
    }
    public static void  getNumberKm(Double longitude ,Double latitude)  {
        try {
            String origin = "Hanoi,%20Vietnam";
            String destination = "Ho%20Chi%20Minh%20City, Vietnam"; // Mã hóa dấu cách
            String apiKey = "AIzaSyCN7wkQpBMR6nIE0pZrLkndpnvJCDm-8ps";

            String apiUrl = "https://maps.googleapis.com/maps/api/distancematrix/json"
                    + "?origins=" + origin.replaceAll(" ", "%20")
                    + "&destinations=" + destination
                    + "&key=" + apiKey;
            System.out.println(apiUrl);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(apiUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseData = response.body();
                JSONObject jsonObject = new JSONObject(responseData);
                JSONArray rows = jsonObject.getJSONArray("rows");
                JSONObject elements = rows.getJSONObject(0).getJSONArray("elements").getJSONObject(0);
                JSONObject distance = elements.getJSONObject("distance");
                int distanceInMeters = distance.getInt("value");

                // Chuyển đổi méta sang kilômét và tính toán chi phí giao hàng
                double distanceInKms = distanceInMeters / 1000.0;
                System.out.println("Distance (km): " + distanceInKms);
                calculateDeliveryCost(distanceInKms, 3, true, true, 50000);

            } else {
                System.err.println("Error: " + response.statusCode());
            }

        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }





    private static void calculateDeliveryCost(double distance, int itemsCount, boolean isNight, boolean isRain, double orderValue) {
        double deliveryCost = 0;
        deliveryCost += BASE_RATE;
        if (distance > 2) {
            deliveryCost += (distance - 2) * ADDITIONAL_RATE;
        }

        if (isNight && (LocalTime.now().isAfter(LocalTime.of(21, 30)) || LocalTime.now().isBefore(LocalTime.of(6, 0)))) {
            deliveryCost += NIGHT_EXTRA_FEE;
        }

        if (isRain) {
            deliveryCost += RAIN_EXTRA_FEE;
        }

        if (itemsCount > 2) {
            deliveryCost += (itemsCount - 2) * EXTRA_PER_ITEM;
        }

        deliveryCost += DOOR_DELIVERY_EXTRA;

        deliveryCost += orderValue * 0.01; // Phụ phí 1% giá trị hoá đơn
        System.out.println("Total delivery cost: " + deliveryCost + " V ");
    }


}
