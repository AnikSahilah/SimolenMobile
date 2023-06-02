package com.example.landingpageactivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.landingpageactivity.ModelResponse.Login.LoginResponse;
import com.example.landingpageactivity.ModelResponse.Login.User;
import com.example.landingpageactivity.ModelResponse.Login.UserData;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Button_login;
    private ApiService apiService;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Inisialisasi Retrofit

        // Buat instance ApiService dari Retrofit
        apiService = ApiClient.getClient();
        sessionManager = new SessionManager(getApplicationContext());
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Button_login = findViewById(R.id.button_login);

//        Button_login.setOnClickListener(view -> {
//            Toast.makeText(getApplicationContext(), "ini di klik", Toast.LENGTH_SHORT).show();
//        });

        Button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                // Panggil method login menggunakan Retrofit
                Call<LoginResponse> call = apiService.login(email, password);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            UserData user = loginResponse.getData();
                            String token = user.getToken();
                            User u = user.getUser();
                            String name = u.getName();
                            String alamat = u.getAlamat();
                            String message = loginResponse.getMessage();
                            String id = String.valueOf(u.getId());
                            String role = String.valueOf(u.getRole());
                            String emails = u.getEmail();
                            String photo = "";

                            // Proses login berhasil, lakukan tindakan yang sesuai

//                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("SIMOLEN", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString(getString(R.string.PREF_EMAIL), token);
//                            editor.apply();
                            sessionManager.login(token,name,alamat,id,role, emails, photo);
                            Log.d(TAG, "onResponse: " + token);
//                            Toast.makeText(getApplicationContext(), String.valueOf(token), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, BottomNav.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Proses login gagal, tangani respons yang tidak berhasil
                            try {
                                String errorBody = response.errorBody().string();
                                Toast.makeText(getApplicationContext(), errorBody, Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onResponse: " + errorBody);
                                // Mengakses respons body saat terjadi error
                                // Anda dapat melakukan pemrosesan atau menampilkan pesan kesalahan yang sesuai di sini
                                // Misalnya, jika server mengembalikan pesan error dalam format JSON, Anda dapat menguraikan JSON untuk mendapatkan pesan kesalahan
                            } catch (IOException e) {
                                e.printStackTrace();
                                // Penanganan exception jika gagal membaca errorBody
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        // Tangani kegagalan koneksi atau permintaan
                        if (t instanceof IOException) {
                            // Kegagalan koneksi jaringan
                            // Lakukan tindakan yang sesuai, seperti menampilkan pesan kesalahan atau notifikasi kepada pengguna
                            String errorMessage = "Koneksi jaringan gagal";
                            // Anda dapat menyesuaikan pesan kesalahan sesuai kebutuhan
                            // Misalnya, menggunakan string resource atau mendapatkan pesan dari sumber eksternal

                            // Contoh tindakan: menampilkan pesan kesalahan
                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        } else {
                            // Kegagalan permintaan, seperti kesalahan server
                            // Lakukan tindakan yang sesuai, seperti menampilkan pesan kesalahan atau notifikasi kepada pengguna
                            String errorMessage = "Kesalahan server";
                            // Anda dapat menyesuaikan pesan kesalahan sesuai kebutuhan
                            // Misalnya, menggunakan string resource atau mendapatkan pesan dari sumber eksternal

                            // Contoh tindakan: menampilkan pesan kesalahan
                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}