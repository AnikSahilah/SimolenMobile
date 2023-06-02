package com.example.landingpageactivity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.landingpageactivity.R;
import com.example.landingpageactivity.SessionManager;
import com.example.landingpageactivity.ViewTab.Adapter.CustomPagerAdapter;
import com.example.landingpageactivity.ViewTab.BengkelFragment;
import com.example.landingpageactivity.ViewTab.SparePartFragment;
import com.example.landingpageactivity.fragment.riwayat.RiwayatMontir;
import com.example.landingpageactivity.fragment.riwayat.RiwayatPembelian;
import com.google.android.material.tabs.TabLayout;

public class RiwayatFragment extends Fragment {

    private SessionManager sessionManager;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_riwayat, container, false);
        sessionManager = new SessionManager(getActivity());
        viewPager = rootView.findViewById(R.id.viewPager);
        tabLayout = rootView.findViewById(R.id.tabLayout);
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getChildFragmentManager());
        if (sessionManager.getKeyRole().contains("customer")){
            pagerAdapter.AddFragment(new RiwayatPembelian(), "Riwayat Saya");
            pagerAdapter.AddFragment(new RiwayatMontir(), "Montir");
        }else {
            pagerAdapter.AddFragment(new RiwayatMontir(), "Montir");
        }
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Menghubungkan ViewPager dengan TabLayout
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

}