package com.example.landingpageactivity;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.landingpageactivity.ModelResponse.Barang.BarangResponse;
import com.example.landingpageactivity.ModelResponse.Bengkel.BengkelResponse;
import com.example.landingpageactivity.ModelResponse.Montir.MontirResponse;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;
import com.example.landingpageactivity.ViewTab.Adapter.BarangAdapter;
import com.example.landingpageactivity.ViewTab.Adapter.MontirAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MontirList extends AppCompatActivity {
    private TextView title_appbar;
    private RecyclerView list;
    private ApiService apiClient;
    private SessionManager sessionManager;
    private String authToken;
    private List<BengkelResponse.MontirData> barangList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montir_list);
        title_appbar = findViewById(R.id.title_appbar);
        list = findViewById(R.id.list_montir);
        title_appbar.setText("Pilih Montir");
        barangList = (List<BengkelResponse.MontirData>) getIntent().getSerializableExtra("bengkelss");

        apiClient = ApiClient.getClient();
        sessionManager = new SessionManager(this);
        authToken = sessionManager.getToken();

        MontirAdapter adapter = new MontirAdapter(barangList);
        list.setAdapter(adapter);
        list.setLayoutManager(new GridLayoutManager(MontirList.this,2));


    }
//
//    private void getData(){
//        Call<BarangResponse> call = apiClient.getM("Bearer " +authToken);
//        call.enqueue(new Callback<BarangResponse>() {
//            @Override
//            public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
//                if (response.isSuccessful()) {
//                    BarangResponse barangResponse = response.body();
//                    List<BarangResponse.BarangData> barangList = barangResponse.getData();
//
//                    BarangAdapter adapter = new BarangAdapter(barangList);
//                    list.setAdapter(adapter);
//                    list.setLayoutManager(new GridLayoutManager(MontirList.this,2));
//                    // Proses data yang diterima
//                } else {
//                    // Tangani kesalahan respons
//                    Toast.makeText(requireActivity(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BarangResponse> call, Throwable t) {
//                // Tangani kegagalan koneksi atau permintaan
//                String message;
//                message = t.getMessage();
//                Log.d(TAG, "onFailure: "+message);
//                Toast.makeText(requireActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
