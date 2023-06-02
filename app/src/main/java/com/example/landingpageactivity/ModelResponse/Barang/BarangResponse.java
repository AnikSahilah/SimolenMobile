package com.example.landingpageactivity.ModelResponse.Barang;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BarangResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<BarangData> data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<BarangData> getData() {
        return data;
    }

    public static class BarangData {
        @SerializedName("id")
        private int id;

        @SerializedName("kode_barang")
        private String kodeBarang;

        @SerializedName("nama_barang")
        private String namaBarang;

        @SerializedName("harga")
        private String harga;

        @SerializedName("merek")
        private String merek;

        @SerializedName("spesifikasi")
        private String spesifikasi;

        @SerializedName("stok")
        private String stok;

        @SerializedName("photo")
        private String photo;

        @SerializedName("bengkel")
        private Barang bengkel;

        public int getId() {
            return id;
        }

        public String getKodeBarang() {
            return kodeBarang;
        }

        public String getNamaBarang() {
            return namaBarang;
        }

        public String getHarga() {
            return harga;
        }

        public String getMerek() {
            return merek;
        }

        public String getSpesifikasi() {
            return spesifikasi;
        }

        public String getStok() {
            return stok;
        }

        public String getPhoto() {
            return photo;
        }

        public Barang getBengkel() {
            return bengkel;
        }
    }

    public static class Barang {
        @SerializedName("nama_bengkel")
        private String namaBengkel;

        @SerializedName("alamat")
        private String alamat;

        @SerializedName("no_hp")
        private String noHp;

        @SerializedName("photo")
        private String photo;

        public String getNamaBengkel() {
            return namaBengkel;
        }

        public String getAlamat() {
            return alamat;
        }

        public String getNoHp() {
            return noHp;
        }

        public String getPhoto() {
            return photo;
        }
    }
}
