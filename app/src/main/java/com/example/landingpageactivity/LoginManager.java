package com.example.landingpageactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;

public class LoginManager {

    private static final String LOGIN_URL = "https://www.simolen.kidzeroll.com/api/v1/login";
    private SessionManager sessionManager;
    private Context context;

    public LoginManager(Context context) {
        sessionManager = new SessionManager(context);
    }

    public void performLogin(String email, String password) {
        try {
            // ... Kode permintaan HTTP ke API ...
            URL url = new URL(LOGIN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            // ... Kode untuk mengirim data login ke server ...
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Login berhasil, simpan data login ke SharedPreference
                redirectToNextScreen(context.getApplicationContext());
            } else {
                // Login gagal, tampilkan pesan kesalahan
                showErrorMessage("Login failed. Please check your credentials.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void redirectToNextScreen(Context activity) {
        Intent intent = new Intent(activity, DashboardCustomerActivity.class);
        activity.startActivity(intent);
    }
    private void showErrorMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

//    private void runOnUiThread(Runnable runnable) {
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.post(runnable);
//    }
}
