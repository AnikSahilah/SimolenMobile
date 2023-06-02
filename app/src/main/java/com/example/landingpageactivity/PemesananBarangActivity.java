package com.example.landingpageactivity;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.landingpageactivity.ModelResponse.Barang.DetailBarangResponse;
import com.example.landingpageactivity.ModelResponse.Barang.PembelianResponse;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;
import com.example.landingpageactivity.Utils.Function;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemesananBarangActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView alamat,counterTextView,harga,namaBarang;
    SessionManager sessionManager;
    private int counter = 0;
    ApiService apiService;
    private String id;
    private String token;
    private String dataHarga;
    private String stok;
    private int idBarang;
    private String idCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan_barang);
        sessionManager = new SessionManager(this);
        Intent intent = getIntent();
        id = String.valueOf(intent.getIntExtra("id",0));
        token = "Bearer " +sessionManager.getToken();
        idCustomer = sessionManager.getIdCustomer();
        imageView = findViewById(R.id.imageView);
        apiService = ApiClient.getClient();
        alamat = findViewById(R.id.alamat);
        harga = findViewById(R.id.harga_bawah);
        namaBarang = findViewById(R.id.nama_barang);
        alamat.setText(sessionManager.getKeyAlamat());
        counterTextView = findViewById(R.id.counterTextView);
        counter = 1;
        counterTextView.setText("1");
        getDetailBarang();
    }
    public void getDetailBarang(){
        Call<DetailBarangResponse> call = apiService.getDetailBarang(token,id);
        call.enqueue(new Callback<DetailBarangResponse>() {
            @Override
            public void onResponse(Call<DetailBarangResponse> call, Response<DetailBarangResponse> response) {
                if (response.isSuccessful()) {
                    DetailBarangResponse detailBarangResponse = response.body();
                    DetailBarangResponse.DetailBarang data = detailBarangResponse.getData();
                    String url = ApiClient.LOCAL +data.getPhoto();
                    Glide.with(getApplicationContext()).load(url).into(imageView);
                    int hargatotal = counter * Integer.parseInt(data.getHarga());
                    dataHarga = data.getHarga();
                    stok = data.getStok();
                    harga.setText(Function.parseRupiah(hargatotal));
                    namaBarang.setText("Nama Barang : " + data.getNamaBarang());
                    idBarang = data.getId();
                    // Lakukan sesuatu dengan respons data detailBarangResponse
                } else {
                    Log.d(TAG, "onFailure: "+response.code());
                    Toast.makeText(getApplicationContext(), "Terjadi kesalahan. Silakan coba lagii.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailBarangResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan. Silakan coba lagi." , Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void createOrder(View view) {
        // Mendapatkan tanggal hari ini
        LocalDate today = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            today = LocalDate.now();
        }

        // Membentuk formatter untuk format "yyyy-MM-dd"
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }

        // Mengubah tanggal menjadi string dengan format yang diinginkan
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = today.format(formatter);
        }

        JsonObject requestBodyJson = new JsonObject();
        requestBodyJson.addProperty("customer_id", Integer.parseInt(idCustomer));
        requestBodyJson.addProperty("barang_id", idBarang);
        requestBodyJson.addProperty("jumlah", counter);
        requestBodyJson.addProperty("tanggal_pembelian", formattedDate);
        // Create the RequestBody using the JSON object
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestBodyJson.toString());
        Log.d(TAG, "createOrder: " + requestBodyJson);
        // Create the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin membuat pesanan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked "Ya", make the API call
                        // Make the API call
                        Call<PembelianResponse> call = apiService.createPembelianBarangData(token, requestBody);
                        call.enqueue(new Callback<PembelianResponse>() {
                            @Override
                            public void onResponse(Call<PembelianResponse> call, Response<PembelianResponse> response) {
                                if (response.isSuccessful()) {
                                    // Handle successful response
                                    PembelianResponse responseBody = response.body();
                                    String message = responseBody.getMessage();
                                    showAlertDialog("Sukses", message);
                                    Log.d(TAG, "onResponse: " + responseBody.toString());
                                    // Process the response data
                                } else {
                                    // Handle error response
                                    String errorMessage = "Error: " + response.code();
                                    // Display an error message or perform other error handling logic
                                    showToast(getApplicationContext(), errorMessage);
                                }
                            }

                            @Override
                            public void onFailure(Call<PembelianResponse> call, Throwable t) {
                                // Handle failure
                                String errorMessage = "Error: " + t.getMessage();
                                Log.d(TAG, "onFailure: " + t.getMessage());
                                // Display an error message or perform other error handling logic
                                showToast(getApplicationContext(), errorMessage);
                            }
                        });
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked "Tidak", do nothing
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public void increaseCounter(View view) {
        if(!String.valueOf(counter).equals(stok)){
            counter++;
            sumHarga();
            counterTextView.setText(String.valueOf(counter));
        }else {
            Toast.makeText(getApplicationContext(),"Maaf Stok Habis",Toast.LENGTH_LONG).show();
        }
    }
    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dismiss the dialog
                        dialog.dismiss();
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void decreaseCounter(View view) {
        if (counter > 0) {
            counter--;
            sumHarga();
            counterTextView.setText(String.valueOf(counter));
        }
    }
    void sumHarga(){

        int hargatotal = counter * Integer.parseInt(dataHarga);
        harga.setText(Function.parseRupiah(hargatotal));
    }
}