package com.example.landingpageactivity;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.landingpageactivity.ModelResponse.RegisterRequest;
import com.example.landingpageactivity.ModelResponse.RegisterResponse;
import com.example.landingpageactivity.RouterAPI.ApiClient;
import com.example.landingpageactivity.RouterAPI.ApiService;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private EditText namaEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText alamatEditText;
    private EditText noHpEditText;

    private Button registerButton;

    private ApiService apiService;
    TextView txt_login;
    private String jk = "";
    RadioButton radioButton;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt_login = findViewById(R.id.txt_login);
        // Inisialisasi elemen UI
//        usernameEditText = findViewById(R.id.username_edittext);
        namaEditText = findViewById(R.id.nama_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edittext);
        alamatEditText = findViewById(R.id.alamat_edittext);
        noHpEditText = findViewById(R.id.no_hp_edittext);
        registerButton= findViewById(R.id.btn_regis);

        radioGroup = findViewById(R.id.jenis_kelamin_radio_group);


        // Inisialisasi ApiService
        apiService = ApiClient.getClient();

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    private void registerUser() {
        String username = namaEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String alamat = alamatEditText.getText().toString().trim();
        String nohp = noHpEditText.getText().toString().trim();
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            radioButton = findViewById(selectedRadioButtonId);

            if(radioButton.getText().toString().contains("Laki")){
                jk = "L";
            }else {
                jk = "P";
            }
        }
        RequestBody requestBody = null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", username);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("password_confirmation", confirmPassword);
            jsonObject.put("alamat", alamat);
            jsonObject.put("no_hp", nohp);
            jsonObject.put("jenis_kelamin", jk);
            Log.d(TAG, "registerUser: " + jsonObject) ;

            // Membuat RequestBody dengan konten JSON
            requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());

            // Gunakan requestBody dalam permintaan Retrofit Anda
            // ...
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RegisterRequest request = new RegisterRequest(username,email, password,confirmPassword,alamat,nohp,jk);
        if (isInputValid(username,email, password, confirmPassword,alamat,nohp)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Konfirmasi");
            builder.setMessage("Apakah data yang dimasukkan sudah benar?");
            RequestBody finalRequestBody = requestBody;
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    RegisterRequest request = new RegisterRequest(username,email, password,confirmPassword,alamat,nohp,jk);
                    performRegistration(finalRequestBody);
                }
            });
            builder.setNegativeButton("Tidak", null);
            builder.show();
        }

    }

    private boolean isInputValid(String nama, String email, String password, String confirmPassword, String alamat, String noHp) {
        if (TextUtils.isEmpty(nama)) {
            showError("Nama tidak boleh kosong");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            showError("Email tidak boleh kosong");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Email tidak valid");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            showError("Password tidak boleh kosong");
            return false;
        }

        if (password.length() < 6) {
            showError("Password harus memiliki minimal 6 karakter");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showError("Password tidak sesuai");
            return false;
        }

        if (TextUtils.isEmpty(alamat)) {
            showError("Alamat tidak boleh kosong");
            return false;
        }

        if (TextUtils.isEmpty(noHp)) {
            showError("Nomor HP tidak boleh kosong");
            return false;
        }

        return true;
    }

    private void performRegistration(RequestBody request) {
        Call<RegisterResponse> call = apiService.registerUser(request);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                if (response.isSuccessful()) {
                    showSuccessDialog();
                } else {
                    showErrorDialog();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: " +t.getMessage());

                showErrorDialog();
            }
        });
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registrasi Berhasil")
                .setMessage("Akun Anda berhasil terdaftar")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registrasi Gagal")
                .setMessage("Terjadi kesalahan saat melakukan registrasi. Silakan coba lagi.")
                .setPositiveButton("OK", null)
                .setCancelable(false)
                .show();
    }

    private void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

}
