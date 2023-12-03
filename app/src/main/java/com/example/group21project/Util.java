package com.example.group21project;

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class Util {
    public void sendFCMNotification(String title, String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // FCM Send Endpoint
                    URL url = new URL("https://fcm.googleapis.com/fcm/send");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setUseCaches(false);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Authorization", "key=" + "AAAAURNu8to:APA91bGXIbEh6FD8RAVdap-nqAu_qv0DpTrxFehbCdnm8ewDwB6PtRpmQXR_0aVr_wMHsiR67gu1xu6PxyDulMQdvaftoOzzkXysTqvUhh062GP0OXyARoZiqQWYRbDI2Do25dqYWmM7");
                    connection.setRequestProperty("Content-Type", "application/json");

                    JSONObject json = new JSONObject();
                    JSONObject notificationObj = new JSONObject();
                    notificationObj.put("title", title);
                    notificationObj.put("body", message);

                    // Replace "your_topic" with the topic you want to send the notification to
                    json.put("to", "/topics/your_topic");
                    json.put("notification", notificationObj);

                    OutputStream os = connection.getOutputStream();
                    os.write(json.toString().getBytes("UTF-8"));
                    os.close();

                    int responseCode = connection.getResponseCode();
                    System.out.println("Response Code : " + responseCode);

                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
