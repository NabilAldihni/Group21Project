package com.example.group21project;

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;
public class Util {

    private static final String PREFERENCES_FILE = "app_preferences";
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

    // Method to save a value to SharedPreferences
    public static void saveToPreferences(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    // Method to retrieve a value from SharedPreferences
    public static String getFromPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    // Method to convert dp to pixels
    public static int dpToPx(Context context, int dp) {
        return Math.round(dp * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    // Method to convert pixels to dp
    public static int pxToDp(Context context, int px) {
        return Math.round(px / (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Method to compress a Bitmap image
    public static byte[] compressImage(Bitmap image, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    // Method to convert a byte array to a Bitmap
    public static Bitmap byteArrayToBitmap(byte[] imageBytes) {
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    // Method to format a date to a specific pattern
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String encryptAES(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Method to decrypt a String using AES
    public static String decryptAES(String encryptedData, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    // Method to generate a secure random string
    public static String generateSecureRandomString() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] randomBytes = new byte[16];
        secureRandom.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }

    // Method to hash a String using SHA-256
    public static String hashStringSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Method for fade in animation
    public static void fadeInView(Context context, View view, int duration) {
        Animation fadeIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        fadeIn.setDuration(duration);
        view.startAnimation(fadeIn);
        view.setVisibility(View.VISIBLE);
    }

    // Method for fade out animation
    public static void fadeOutView(Context context, View view, int duration) {
        Animation fadeOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        fadeOut.setDuration(duration);
        view.startAnimation(fadeOut);
        view.setVisibility(View.INVISIBLE);
    }

    // Method for slide in animation
    public static void slideInView(Context context, View view, int resId) {
        Animation slideIn = AnimationUtils.loadAnimation(context, resId);
        view.startAnimation(slideIn);
        view.setVisibility(View.VISIBLE);
    }

    // Method for rotate animation
    public static void rotateView(View view, long duration, float fromDegree, float toDegree) {
        view.animate().rotation(toDegree).setDuration(duration).start();
    }

    // Method for scale animation
    public static void scaleView(View view, float fromScale, float toScale, long duration) {
        view.setScaleX(fromScale);
        view.setScaleY(fromScale);
        view.animate().scaleX(toScale).scaleY(toScale).setDuration(duration).start();
    }

    // Method to check internet connectivity
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    // Method to format currency
    public static String formatCurrency(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return format.format(amount);
    }

    // Method to get color from resources
    public static int getColorFromResources(Context context, int resId) {
        return ContextCompat.getColor(context, resId);
    }

    // Method to read file from assets
    public static String readFileFromAssets(Context context, String fileName) throws IOException {
        InputStream is = context.getAssets().open(fileName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, "UTF-8");
    }

    // Method to show a toast message
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // Method for standardized logging
    public static void logMessage(String tag, String message) {
        Log.d(tag, message);
    }

    // Method to open a web page for POSt requirements
    public static void openPostRequirementsWebPage(Context context, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

    // Method to send an email (e.g., for submitting complaints)
    public static void sendEmail(Context context, String[] recipients, String subject, String body) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        if (emailIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(emailIntent);
        }
    }

    // Method to schedule a notification for an event
    public static void scheduleEventNotification(Context context, String title, String content, long when) {
        // This method would interact with AlarmManager to schedule a notification.
        // You would need to implement a BroadcastReceiver to handle the broadcast
        // and trigger the notification at the scheduled time.
    }

    // Method to collect and store event feedback
    public static void storeEventFeedback(Context context, String eventId, String comment, int rating) {
        // This method would store the feedback in a local database or send it to a server.
        // The specifics would depend on how you manage data persistence.
    }

    // Method to calculate the average of numeric ratings
    public static double calculateAverageRating(List<Integer> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (int rating : ratings) {
            sum += rating;
        }
        return sum / ratings.size();
    }

    // Method for admin to post announcements (e.g., to a server)
    public static void postAnnouncement(String announcementText) {
        // This method would contain the logic to post the announcement to a server
        // or another platform that is being used for announcements.
    }

    // Method for admin to view student complaints
    public static List<Complaint> getStudentComplaints() {
        // This method would retrieve complaints from a local database or server.
        // A 'Complaint' class would need to be defined elsewhere in your code.
        return new ArrayList<>();
    }
}
