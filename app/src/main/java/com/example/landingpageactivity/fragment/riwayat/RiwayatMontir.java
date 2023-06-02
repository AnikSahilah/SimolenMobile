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
import com.example.landingpageactivity.ModelResponse.Login.UserData;
import com.example.landingpageactivity.ModelResponse.Montir.GetPemesananMontir;
import com.example.landingpageactivity.R;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;
import com.example.landingpageactivity.SessionManager;
import com.example.landingpageactivity.fragment.riwayat.adapter.RiwayatAdapter;
import com.example.landingpageactivity.fragment.riwayat.adapter.RiwayatMonAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatMontir extends Fragment {

    private RecyclerView recyclerView;
    private RiwayatAdapter riwayatAdapter;

    private PembelianResponse.PembelianData riwayatList;
    private SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rmontir, container, false);
//        riwayatList = new ArrayList<>();
        // Inisialisasi RecyclerView
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sessionManager = new SessionManager(requireActivity());
        // Inisialisasi dan mengatur adapter RecyclerView
//        riwayatAdapter = new RiwayatAdapter();
//        riwayatAdapter.setRiwayatList(riwayatList);
        recyclerView.setAdapter(riwayatAdapter);
        getRiwayatBarang();
        return view;
    }
    private void getRiwayatBarang() {
        // Mendapatkan instance dari ApiInterface menggunakan ApiClient
        ApiService apiInterface = ApiClient.getClient();

        // Mengirimkan header Authorization jika diperlukan
        String authToken = "Bearer " +sessionManager.getToken();

        // Memanggil endpoint pembelian-barang
        Call<GetPemesananMontir> call = apiInterface.getRiwayatMonitr(authToken);

        // Melakukan request secara asynchronous
        call.enqueue(new Callback<GetPemesananMontir>() {
            @Override
            public void onResponse(Call<GetPemesananMontir> call, Response<GetPemesananMontir> response) {
                if (response.isSuccessful()) {
                    GetPemesananMontir pembelianResponse = response.body();
                    if (pembelianResponse != null) {
//                        PembelianResponse.PembelianData riwayatList = pembelianResponse.getData();
                        RiwayatMonAdapter riwayatMonAdapter = new RiwayatMonAdapter();
                        riwayatAdapter = new RiwayatAdapter();
                        if(sessionManager.getKeyRole().equalsIgnoreCase("customer")){
                            recyclerView.setAdapter(riwayatAdapter);
                            riwayatAdapter.setRiwayatMontir(pembelianResponse.getData());
                        }else {
                            recyclerView.setAdapter(riwayatMonAdapter);
                            riwayatMonAdapter.setRiwayatMontir(pembelianResponse.getData());
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to get riwayat barang" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetPemesananMontir> call, Throwable t) {
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
