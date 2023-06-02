package com.example.landingpageactivity.ViewTab;
import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.landingpageactivity.ModelResponse.Barang.BarangResponse;
import com.example.landingpageactivity.ModelResponse.Bengkel.BengkelResponse;
import com.example.landingpageactivity.ModelResponse.Montir.MontirResponse;
import com.example.landingpageactivity.R;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;
import com.example.landingpageactivity.SessionManager;
import com.example.landingpageactivity.ViewTab.Adapter.BarangAdapter;
import com.example.landingpageactivity.ViewTab.Adapter.BengkelAdapter;
import com.example.landingpageactivity.ViewTab.Adapter.SparePartAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BengkelFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<String> dataList;
    private SparePartAdapter adapter;

    ApiService apiClient;

    String authToken;
    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bengkel_layout, container, false);
        recyclerView = view.findViewById(R.id.list_barang);
        apiClient = ApiClient.getClient();
        sessionManager = new SessionManager(requireActivity());
        authToken = sessionManager.getToken();
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
//        dataList = new ArrayList<>();
//        dataList.add("Item 1");
//        dataList.add("Item 2");
//        dataList.add("Item 3");
//        dataList.add("Item 4");
//        dataList.add("Item 5");
//        adapter = new SparePartAdapter(dataList);
//        recyclerView.setAdapter(adapter);
        if(authToken!=null){
            getData();
        }
        return view;
    }

    private void getData(){
        Call<BengkelResponse> call = apiClient.getBengkelData("Bearer " +authToken);
        call.enqueue(new Callback<BengkelResponse>() {
            @Override
            public void onResponse(Call<BengkelResponse> call, Response<BengkelResponse> response) {
                if (response.isSuccessful()) {
                    BengkelResponse barangResponse = response.body();
                    List<BengkelResponse.PemesananBengkelData> barangList = barangResponse.getData();
                    BengkelAdapter adapter = new BengkelAdapter(barangList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    // Proses data yang diterima
                } else {
                    // Tangani kesalahan respons
                    Toast.makeText(requireActivity(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BengkelResponse> call, Throwable t) {
                // Tangani kegagalan koneksi atau permintaan
                String message;
                message = t.getMessage();
                Log.d(TAG, "onFailure: "+message);
                Toast.makeText(requireActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
