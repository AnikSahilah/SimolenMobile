package com.example.landingpageactivity.fragment.riwayat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.landingpageactivity.ModelResponse.Barang.GetPembelianResponse;
import com.example.landingpageactivity.ModelResponse.Barang.PembelianResponse;
import com.example.landingpageactivity.R;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;
import com.example.landingpageactivity.SessionManager;
import com.example.landingpageactivity.fragment.riwayat.adapter.RiwayatAdapter;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPembelian extends Fragment {

    private RecyclerView recyclerView;
    private RiwayatAdapter riwayatAdapter;
    private SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rpembelian, container, false);
        sessionManager = new SessionManager(requireContext());
        // Inisialisasi RecyclerView
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inisialisasi dan mengatur adapter RecyclerView
        getRiwayatBarang();
        return view;
    }
    private void getRiwayatBarang() {
        // Mendapatkan instance dari ApiInterface menggunakan ApiClient
        ApiService apiInterface = ApiClient.getClient();

        // Mengirimkan header Authorization jika diperlukan
        String authToken = "Bearer " +sessionManager.getToken();

        // Memanggil endpoint pembelian-barang
        Call<GetPembelianResponse> call = apiInterface.getRiwayatBarang(authToken);

        // Melakukan request secara asynchronous
        call.enqueue(new Callback<GetPembelianResponse>() {
            @Override
            public void onResponse(Call<GetPembelianResponse> call, Response<GetPembelianResponse> response) {
                if (response.isSuccessful()) {
                    GetPembelianResponse pembelianResponse = response.body();
                    if (pembelianResponse != null) {
//                        PembelianResponse.PembelianData riwayatList = pembelianResponse.getData();
                        riwayatAdapter = new RiwayatAdapter();
                        recyclerView.setAdapter(riwayatAdapter);
                        riwayatAdapter.setRiwayatList(pembelianResponse.getData());
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to get riwayat barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetPembelianResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getRiwayatBarang();
    }
}
