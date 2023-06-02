package com.example.landingpageactivity.fragment.riwayat.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.landingpageactivity.ModelResponse.Barang.GetPembelianResponse;
import com.example.landingpageactivity.ModelResponse.Montir.GetPemesananMontir;
import com.example.landingpageactivity.R;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;
import com.example.landingpageactivity.SessionManager;
import com.example.landingpageactivity.Utils.Function;
import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatMonAdapter extends RecyclerView.Adapter<RiwayatMonAdapter.RiwayatViewHolder> {

    private List<GetPembelianResponse.PembelianData> riwayatList;
    List<GetPemesananMontir.DataModel> datas;
//    Activity activity;
    public RiwayatMonAdapter() {
        riwayatList = new ArrayList<>();
//        activity = activity;
        datas = new ArrayList<>();
    }
    public void setRiwayatMontir(List<GetPemesananMontir.DataModel> data) {
        this.datas = data;
        Collections.reverse(this.datas);
        notifyDataSetChanged();
    }
    public void setRiwayatList(List<GetPembelianResponse.PembelianData> riwayatList) {
        this.riwayatList = riwayatList;
        Collections.reverse(this.riwayatList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesan, parent, false);
        return new RiwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
            GetPemesananMontir.DataModel i = datas.get(position);
            holder.binds(i);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class RiwayatViewHolder extends RecyclerView.ViewHolder {

        // TODO: Declare your views here
//        ImageView imageView;
        TextView namaTextView;
        TextView lokasiTextView;
        TextView kerusakanTextView;
        TextView kendaraanTextView;
        MaterialButton tolakButton;
        MaterialButton terimaButton;

        View i;
        String ids;
        Context activity;
        RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            i = itemView;
//            imageView = itemView.findViewById(R.id.gambar_kecil);

            namaTextView = itemView.findViewById(R.id.namaTextView);
            lokasiTextView = itemView.findViewById(R.id.lokasiTextView);
            kerusakanTextView = itemView.findViewById(R.id.kerusakanTextView);
            kendaraanTextView = itemView.findViewById(R.id.kendaraanTextView);
            tolakButton = itemView.findViewById(R.id.tolakButton);
            terimaButton = itemView.findViewById(R.id.terimaButton);
            activity = itemView.getContext();
            // TODO: Initialize your views here
        }
        @SuppressLint("SetTextI18n")
        void binds(GetPemesananMontir.DataModel i){
                GetPemesananMontir.CustomerModel customerModel = i.getCustomer();
                GetPemesananMontir.MontirModel montirModel = i.getMontir();
                ids = String.valueOf(i.getId());
                namaTextView.setText("Nama Customer : " +customerModel.getNama());
//                harga.setText(customerModel.getNo_hp());
                lokasiTextView.setText("Status : "+i.getStatus());
                tolakButton.setOnClickListener(view -> {
                    Toast.makeText(activity,"Masih Tahap Perbaikan",Toast.LENGTH_LONG).show();
                });
            terimaButton.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah Anda yakin ingin selesaikan pemesanan?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        approve();
                    }
                });
                builder.setNegativeButton("Tidak", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            });
        }
//        void bind(GetPembelianResponse.PembelianData riwayatItem) {
//            // TODO: Bind data to your views here
//            GetPembelianResponse.BarangData item = riwayatItem.getBarang();
//            String url = ApiClient.LOCAL +item.getPhoto();
//            Glide.with(i).load(url).into(imageView);
//            int stok = riwayatItem.getJumlah();
//            int har = stok * Integer.parseInt(item.getHarga());
//            harga.setText(Function.parseRupiah(har));
//            produk.setText(stok + " Produk");
//            ids = String.valueOf(riwayatItem.getId());
//            nama_barang.setText(item.getNamaBarang());
//            status.setText("Status Pembelian : "+riwayatItem.getStatus());
//
//            button.setOnClickListener(view -> {
//                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                builder.setTitle("Konfirmasi");
//                builder.setMessage("Apakah Anda yakin ingin selesaikan pemesanan?");
//                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        approve();
//                    }
//                });
//                builder.setNegativeButton("Tidak", null);
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            });
//
//        }
        void approve(){
            SessionManager sessionManager = new SessionManager(activity);
            String authToken = "Bearer " + sessionManager.getToken() ; // Replace with the actual authorization token
            ApiService apiService = ApiClient.getClient();
            Call<JsonObject> call = apiService.approveMontir(authToken, ids);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        // API call successful, do something with the response
                        JSONObject jsonObject = null;
                        String message;
                        try {
                            jsonObject = new JSONObject(response.body().toString());
                            message = jsonObject.getString("message");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle("Success");
                        builder.setMessage(message);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss(); // Finish the activity
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        // API call failed, handle the error
                        Toast.makeText(activity,response.message(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    // Handle network failure or other errors
                    Toast.makeText(activity,t.getMessage(),Toast.LENGTH_LONG).show();

                }
            });

        }
    }
}
