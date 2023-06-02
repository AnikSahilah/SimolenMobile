package com.example.landingpageactivity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.landingpageactivity.fragment.DashboardFragment;
import com.example.landingpageactivity.fragment.DashboardMontirFragment;
import com.example.landingpageactivity.fragment.ProfilFragment;
import com.example.landingpageactivity.fragment.RiwayatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNav extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);
        sessionManager = new SessionManager(this);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked},
                new int[]{}
        };

        int[] colors = new int[]{
                getResources().getColor(R.color.toska),
                getResources().getColor(R.color.grey)
        };
// Create a ColorStateList with the desired color

        ColorStateList iconColor = new ColorStateList(states, colors);

// Set the ColorStateList as the icon tint for the BottomNavigationView
        bottomNavigationView.setItemIconTintList(iconColor);
        bottomNavigationView.setItemTextColor(iconColor);
        // Menampilkan fragment Home saat pertama kali aplikasi dibuka
        loadFragment(sessionManager.getKeyRole().contains("customer") ? new DashboardFragment() : new DashboardMontirFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.menu_home:
                fragment = sessionManager.getKeyRole().contains("customer") ? new DashboardFragment() : new DashboardMontirFragment();
                break;
            case R.id.menu_riwayat:
                fragment = new RiwayatFragment();
                break;
            case R.id.menu_profil:
                fragment = new ProfilFragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}

