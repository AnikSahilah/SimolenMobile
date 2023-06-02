package com.example.landingpageactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.landingpageactivity.ModelResponse.Barang.BarangResponse;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;
import com.example.landingpageactivity.ViewTab.Adapter.BarangAdapter;
import com.example.landingpageactivity.ViewTab.BengkelFragment;
import com.example.landingpageactivity.ViewTab.Adapter.CustomPagerAdapter;
import com.example.landingpageactivity.ViewTab.SparePartFragment;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardCustomerActivity extends AppCompatActivity {
    ImageView image;
    SessionManager sessionManager;
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView name;
    String authToken;

    ApiService apiClient;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_customer);
        sessionManager = new SessionManager(this);
        image = findViewById(R.id.simage);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        name = findViewById(R.id.name);
        name.setText(sessionManager.getKeyName());
        authToken =  sessionManager.getToken();
        apiClient = ApiClient.getClient();
        // Membuat objek adapter untuk ViewPager
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        pagerAdapter.AddFragment(new SparePartFragment(),"Spare Part");
        pagerAdapter.AddFragment(new BengkelFragment(),"Bengkel");
        // Mengatur adapter untuk ViewPager
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // Menghubungkan ViewPager dengan TabLayout
        tabLayout.setupWithViewPager(viewPager);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }

}