package com.example.landingpageactivity.ModelResponse;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("password_confirmation")
    private String passwordConfirmation;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

    public RegisterRequest(String name, String email, String password, String passwordConfirmation, String alamat, String noHp, String jenisKelamin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.alamat = alamat;
        this.noHp = noHp;
        this.jenisKelamin = jenisKelamin;
    }

    // Getter and setter methods
}

