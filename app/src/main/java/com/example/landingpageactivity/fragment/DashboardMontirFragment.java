package com.example.landingpageactivity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.landingpageactivity.MainActivity;
import com.example.landingpageactivity.R;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;
import com.example.landingpageactivity.SessionManager;
import com.example.landingpageactivity.ViewTab.Adapter.CustomPagerAdapter;
import com.example.landingpageactivity.ViewTab.BengkelFragment;
import com.example.landingpageactivity.ViewTab.SparePartFragment;
import com.example.landingpageactivity.fragment.riwayat.RiwayatMontir;
import com.google.android.material.tabs.TabLayout;

public class DashboardMontirFragment extends Fragment {
    private ImageView image;
    private SessionManager sessionManager;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView name;
    private String authToken;

    private ApiService apiClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_dashboard_montir, container, false);

        sessionManager = new SessionManager(getActivity());
        image = rootView.findViewById(R.id.simage);
        viewPager = rootView.findViewById(R.id.viewPager);
        tabLayout = rootView.findViewById(R.id.tabLayout);
        name = rootView.findViewById(R.id.name);
        name.setText(sessionManager.getKeyName());
        authToken = sessionManager.getToken();
        apiClient = ApiClient.getClient();

        // Membuat objek adapter untuk ViewPager
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getChildFragmentManager());
        pagerAdapter.AddFragment(new RiwayatMontir(), "Pemesanan");
//        pagerAdapter.AddFragment(new BengkelFragment(), "Bengkel");

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
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        return rootView;
    }
}
