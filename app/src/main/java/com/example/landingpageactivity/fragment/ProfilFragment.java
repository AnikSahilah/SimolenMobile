package com.example.landingpageactivity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.landingpageactivity.R;
import com.example.landingpageactivity.SessionManager;

public class ProfilFragment extends Fragment {
    SessionManager sessionManager;
    TextView name,email;
    ImageView image;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.activity_profile_costumer, container, false);
        sessionManager = new SessionManager(requireActivity());
        name = view.findViewById(R.id.name);
        image = view.findViewById(R.id.imageView);
        email =view.findViewById(R.id.email);
        email.setText(sessionManager.getKeyEmail());
        name.setText(sessionManager.getKeyName());
        return view;
    }
}