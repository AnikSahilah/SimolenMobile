package com.example.landingpageactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.landingpageactivity.ModelResponse.Bengkel.BengkelResponse;
import com.example.landingpageactivity.ModelResponse.Montir.PemesananMontir;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemesananMontirActivity extends AppCompatActivity {
    private TextView title_appbar,name,no_hp,lokasi;
    private Button button;
    private ImageView imageView;
    private EditText namaEditText,alamatEditText,kerusakanEditText,noHpEditText;
    private SessionManager sessionManager;
    BengkelResponse.MontirData montirData;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan_montir);
        sessionManager = new SessionManager(this);
        namaEditText = findViewById(R.id.nama_edittext);
        alamatEditText = findViewById(R.id.alamat_edittext);
        kerusakanEditText = findViewById(R.id.kerusakan);
        noHpEditText = findViewById(R.id.no_hp_edittext);
        lokasi = findViewById(R.id.lokasi);
        button = findViewById(R.id.pesan);
        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        no_hp = findViewById(R.id.no_hp_montir);
        montirData = (BengkelResponse.MontirData) getIntent().getSerializableExtra("montir");
        if(montirData !=null){
            id = String.valueOf(montirData.getId());
            String url = ApiClient.LOCAL +montirData.getPhoto();
            name.setText(montirData.getName());
            no_hp.setText(montirData.getStatus());
            Glide.with(this).load(url).into(imageView);
        }
        title_appbar = findViewById(R.id.title_appbar);
        title_appbar.setText("PEMESANAN MONTIR");
        String list[]={"Mobil","Motor"};
        Spinner spinner = (Spinner) findViewById(R.id.spiner1);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(AdapterList);
        button.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Konfirmasi");
            builder.setMessage("Apakah Anda yakin ingin membuat pemesanan?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    createPemesanan();
                }
            });
            builder.setNegativeButton("Tidak", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        });


    }
    public void createPemesanan(){
        String authToken = "Bearer " + sessionManager.getToken(); // Ganti dengan token otorisasi yang sesuai
//        String nama = namaEditText.getText().toString();
//        String alamat = alamatEditText.getText().toString();
//        String kerusakan = kerusakanEditText.getText().toString();
//        String noHp = noHpEditText.getText().toString();

        // Membuat permintaan tubuh (request body)
        RequestBody requestBody = null;
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("customer_id", sessionManager.getIdCustomer());
            jsonBody.put("montir_id", id);
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentDate);
            jsonBody.put("tanggal_pemesanan", formattedDate); // Ganti dengan nilai tanggal_pemesanan yang sesuai

            String requestBodyString = jsonBody.toString();
            requestBody = RequestBody.create(MediaType.parse("application/json"), requestBodyString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiService apiService = ApiClient.getClient();
        Call<PemesananMontir> call = apiService.createPemesananMontir(authToken, requestBody);

        call.enqueue(new Callback<PemesananMontir>() {
            @Override
            public void onResponse(Call<PemesananMontir> call, Response<PemesananMontir> response) {
                if (response.isSuccessful()) {
                    PemesananMontir pemesananMontir = response.body();
                    // Berhasil melakukan permintaan, lakukan sesuatu dengan respons
                    AlertDialog.Builder builder = new AlertDialog.Builder(PemesananMontirActivity.this);
                    builder.setTitle("Success");
                    builder.setMessage(pemesananMontir.getMessage());
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish(); // Finish the activity
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    // Permintaan gagal, tangani kesalahan
                    Toast.makeText(PemesananMontirActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PemesananMontir> call, Throwable t) {
                // Tangani kegagalan jaringan atau kesalahan lainnya
                Toast.makeText(PemesananMontirActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}