package com.example.shipnhanh.utills;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CallAPIMBank {


    public static void contentApiBank(String account ,String password) {
        // Thông số cần thay đổi
        String taikhoanmb = ""; // Tài khoản đăng nhập mbbank của bạn tại https://online.mbbank.com.vn
        String deviceIdCommon = ""; // Thay thông số này mà bạn lấy được từ F12 vào đây
        String sessionId = ""; // Thay thông số này mà bạn lấy được từ F12 vào đây
        String sotaikhoanmb = "";

        // Phần này giữ nguyên
        String timezone = "Asia/Ho_Chi_Minh";
        String time1 = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", System.currentTimeMillis() - 60 * 60 * 24 * 1000) + "00";
        String time2 = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", System.currentTimeMillis()) + "00";
        String todate = String.format("%1$td/%1$tm/%1$tY", System.currentTimeMillis());
        String url = "https://online.mbbank.com.vn/retail_web/common/getTransactionHistory";

        try {
            Map<String, String> data = new HashMap<>();
            data.put("accountNo", sotaikhoanmb);
            data.put("deviceIdCommon", deviceIdCommon);
            data.put("fromDate", todate);
            data.put("historyNumber", "");
            data.put("historyType", "DATE_RANGE");
            data.put("refNo", taikhoanmb + "-" + time2);
            data.put("sessionId", sessionId);
            data.put("toDate", todate);
            data.put("type", "ACCOUNT");

            String postdata = mapToJSON(data);

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Gửi yêu cầu POST
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postdata.getBytes(StandardCharsets.UTF_8));
            }

            // Đọc phản hồi
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println(response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String mapToJSON(Map<String, String> data) {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            result.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }
        result.deleteCharAt(result.length() - 1); // Remove trailing comma
        result.append("}");
        return result.toString();
    }

}
