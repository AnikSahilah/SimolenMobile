package com.example.landingpageactivity.RouterAPI;

import com.example.landingpageactivity.ModelResponse.Barang.BarangResponse;
import com.example.landingpageactivity.ModelResponse.Barang.DetailBarangResponse;
import com.example.landingpageactivity.ModelResponse.Barang.GetPembelianResponse;
import com.example.landingpageactivity.ModelResponse.Barang.PembelianResponse;
import com.example.landingpageactivity.ModelResponse.Bengkel.BengkelResponse;
import com.example.landingpageactivity.ModelResponse.Login.LoginResponse;
import com.example.landingpageactivity.ModelResponse.Montir.GetPemesananMontir;
import com.example.landingpageactivity.ModelResponse.Montir.MontirResponse;
import com.example.landingpageactivity.ModelResponse.Montir.PemesananMontir;
import com.example.landingpageactivity.ModelResponse.RegisterRequest;
import com.example.landingpageactivity.ModelResponse.RegisterResponse;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("barang")
    Call<BarangResponse> getBarangData(@Header("Authorization") String authToken);

    @GET("barang/{id}")
    Call<DetailBarangResponse> getDetailBarang(@Header("Authorization") String authToken,@Path("id") String id);

    @GET("bengkel")
    Call<BengkelResponse> getBengkelData(@Header("Authorization") String authToken);

    @GET("montir")
    Call<MontirResponse> getMontirData(@Header("Authorization") String authToken);

    @GET("pembelian-barang")
    Call<GetPembelianResponse> getRiwayatBarang(@Header("Authorization") String authToken);
    @POST("pembelian-barang")
    Call<PembelianResponse> createPembelianBarangData(@Header("Authorization") String authToken, @Body RequestBody requestBody);

    @PUT("/api/v1/pembelian-barang/{id}/approve/customer")
    Call<JsonObject> approvePembelian(@Header("Authorization") String authToken,@Path("id") String id);
    @PUT("pemesanan-montir/{id}/approve/customer")
    Call<JsonObject> approveMontirCust(@Header("Authorization") String authToken,@Path("id") String id,@Body RequestBody requestBody);

    @PUT("/api/v1/pemesanan-montir/{id}/approve")

    Call<JsonObject> approveMontir(@Header("Authorization") String authToken,@Path("id") String id);
    @POST("pemesanan-montir")
    Call<PemesananMontir> createPemesananMontir(@Header("Authorization") String authToken, @Body RequestBody requestBody);

    @GET("pemesanan-montir")
    Call<GetPemesananMontir> getRiwayatMonitr(@Header("Authorization") String authToken);
    @POST("register")
    Call<RegisterResponse> registerUser(@Body RequestBody request);

}
