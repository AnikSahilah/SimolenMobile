package com.example.landingpageactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 2000; // Contoh: 2 detik
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Mengecek nilai yang disimpan di SharedPreferences
        sessionManager = new SessionManager(getApplicationContext());
        SharedPreferences sharedPreferences = getSharedPreferences("SIMOLEN", MODE_PRIVATE);
        String value = sharedPreferences.getString(getString(R.string.PREF_EMAIL), "");

        // Jika nilai "PREF_EMAIL" adalah "1", langsung menuju ke DashboardCustomerActivity
//        Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
        if (sessionManager.isLoggedIn()) {
            Toast.makeText(this, "Login Sukses!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, BottomNav.class);
            startActivity(intent);
            finish();
        } else {
            // Jika nilai "PREF_EMAIL" bukan "1", tampilkan splash screen selama beberapa waktu
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LandingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }
}