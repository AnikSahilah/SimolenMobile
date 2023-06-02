package com.example.landingpageactivity.fragment.riwayat.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {

    private List<GetPembelianResponse.PembelianData> riwayatList;
    List<GetPemesananMontir.DataModel> datas;
//    String role; /  x
    public RiwayatAdapter() {
//        this.role = role;
        riwayatList = new ArrayList<>();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat, parent, false);
        return new RiwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
        if(riwayatList.size() !=0){
            GetPembelianResponse.PembelianData riwayatItem = riwayatList.get(position);
            holder.bind(riwayatItem);
        }else {
            GetPemesananMontir.DataModel i = datas.get(position);
            holder.binds(i);
        }
    }
    @Override
    public int getItemCount() {
        if(riwayatList.size() != 0){
            return riwayatList.size();
        }else {
            return datas.size();
        }
    }


    static class RiwayatViewHolder extends RecyclerView.ViewHolder {

        // TODO: Declare your views here
        ImageView imageView;
        TextView nama_barang,harga,produk,status;
        Button button;
        View i;
        String ids;
        Context activity;
        RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            i = itemView;
            imageView = itemView.findViewById(R.id.gambar_kecil);
            nama_barang = itemView.findViewById(R.id.nama_barang);
            harga = itemView.findViewById(R.id.total_harga);
            produk = itemView.findViewById(R.id.produk);
            status = itemView.findViewById(R.id.status);
            activity = itemView.getContext();
            button = itemView.findViewById(R.id.button);
            // TODO: Initialize your views here
        }
        @SuppressLint("SetTextI18n")
        void binds(GetPemesananMontir.DataModel i){
                GetPemesananMontir.CustomerModel customerModel = i.getCustomer();
                GetPemesananMontir.MontirModel montirModel = i.getMontir();
                nama_barang.setText(montirModel.getNama());
                harga.setText(customerModel.getNo_hp());
                ids = String.valueOf(i.getId());
                status.setText("Status : "+i.getStatus());
                if(i.getStatus().toLowerCase().contains("menunggu")){
                    button.setEnabled(false);
                    button.setBackgroundColor(activity.getResources().getColor(R.color.grey));
                } else if (i.getStatus().toLowerCase().contains("selesai")) {
                    button.setEnabled(false);
                    button.setBackgroundColor(activity.getResources().getColor(R.color.grey));
                } else {
                    button.setOnClickListener(view -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle("Masukan Total Pembayaran");

                        // Membuat layout untuk AlertDialog dengan EditText
                        LinearLayout layout = new LinearLayout(activity);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        final EditText inputEditText = new EditText(activity);
                        inputEditText.setLayoutParams(layoutParams);
                        inputEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        layout.addView(inputEditText);
                        builder.setView(layout);

                        // Set button actions
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String inputText = inputEditText.getText().toString();
                                // Lakukan sesuatu dengan inputText yang diberikan
                                // Misalnya, tampilkan pesan dengan Toast
                                approve2(inputText);
//                                Toast.makeText(activity, "Input Text: " + inputText, Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", null);

                        AlertDialog dialog = builder.create();
                        dialog.show();

//                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                        builder.setTitle("Konfirmasi");
//                        builder.setMessage("Apakah Anda yakin ingin selesaikan pemesanan?");
//                        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                            }
//                        });
//                        builder.setNegativeButton("Tidak", null);
//                        AlertDialog dialog = builder.create();
//                        dialog.show();
                    });
                }
        }
        void bind(GetPembelianResponse.PembelianData riwayatItem) {
            // TODO: Bind data to your views here
            GetPembelianResponse.BarangData item = riwayatItem.getBarang();
            String url = ApiClient.LOCAL +item.getPhoto();
            Glide.with(i).load(url).into(imageView);
            int stok = riwayatItem.getJumlah();
            int har = stok * Integer.parseInt(item.getHarga());
            harga.setText(Function.parseRupiah(har));
            produk.setText(stok + " Produk");
            ids = String.valueOf(riwayatItem.getId());
            nama_barang.setText(item.getNamaBarang());
            status.setText("Status Pembelian : "+riwayatItem.getStatus());
            if(riwayatItem.getStatus().toLowerCase().contains("belum")){
                button.setOnClickListener(view -> {
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
            }else {
                button.setEnabled(false);
                button.setBackgroundColor(activity.getResources().getColor(R.color.grey));
            }

        }
        void approve2(String inputText){
            SessionManager sessionManager = new SessionManager(activity);
            String authToken = "Bearer " + sessionManager.getToken() ; // Replace with the actual authorization token
            ApiService apiService = ApiClient.getClient();
            JSONObject jsonObject = new JSONObject();
            String total = new String();
            try {
                jsonObject.put("total",inputText);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
            Call<JsonObject> call = apiService.approveMontirCust(authToken, ids,requestBody);
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
        void approve(){
            SessionManager sessionManager = new SessionManager(activity);
            String authToken = "Bearer " + sessionManager.getToken() ; // Replace with the actual authorization token
            ApiService apiService = ApiClient.getClient();
            Call<JsonObject> call = apiService.approvePembelian(authToken, ids);
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
