package com.example.landingpageactivity;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String LOGIN_URL = "http://192.168.1.13:8000/api/v1/login";
    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_EMAIL = "email";
    private static final String KEYIMAGE = "image";
    private static final String TOKEN = "token";
    private static final String KEY_ROLE = "role";
    private static final String ID_CUSTOMER = "id";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void login(String token, String name, String alamat, String id, String role, String email, String photo) {
        editor.putString(TOKEN, token);
        editor.putString(KEY_NAME, name);
        editor.putString(ID_CUSTOMER,id);
        editor.putString(KEY_ALAMAT,alamat);
        editor.putString(KEY_ROLE,role);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEYIMAGE,photo);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.contains(TOKEN);
    }

    public void logout() {
        editor.remove(TOKEN);
        editor.remove(KEY_NAME);
        editor.remove(KEY_ALAMAT);
        editor.remove(KEY_ROLE);
        editor.remove(ID_CUSTOMER);
        editor.remove(KEY_ROLE);
        editor.remove(KEY_EMAIL);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, null);
    }
    public String getKeyRole() {
        return sharedPreferences.getString(KEY_ROLE, null);
    }

    public String getKeyName() {
        return sharedPreferences.getString(KEY_NAME, null);
    }

    public String getKeyAlamat() {
        return sharedPreferences.getString(KEY_ALAMAT, null);

    }

    public String getIdCustomer() {
        return sharedPreferences.getString(ID_CUSTOMER, null);

    }

    public String getKeyEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);

    }
}
