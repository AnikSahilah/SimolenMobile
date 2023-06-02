package com.example.landingpageactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RiwayatMontirActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String s1[], s2[], s3[], s4[], s5[];
    int images[] = {R.drawable.profile, R.drawable.profile, R.drawable.profile, R.drawable.profile,
            R.drawable.profile, R.drawable.profile, R.drawable.profile};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_montir);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.nama);
        s2 = getResources().getStringArray(R.array.lokasi);
        s3 = getResources().getStringArray(R.array.jeniskendaraan);
        s4 = getResources().getStringArray(R.array.nama);
        s5 = getResources().getStringArray(R.array.komentar);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, s4, s5, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}