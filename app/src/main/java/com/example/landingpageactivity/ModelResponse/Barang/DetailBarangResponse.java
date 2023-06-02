package com.example.landingpageactivity.ModelResponse.Barang;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

public class DetailBarangResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private DetailBarang data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DetailBarang getData() {
        return data;
    }

    public static class DetailBarang {
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
        private Bengkel bengkel;

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

        public Bengkel getBengkel() {
            return bengkel;
        }
    }

    public static class Bengkel {
        @SerializedName("nama_bengkel")
        private String namaBengkel;

        @SerializedName("alamat")
        private String alamat;

        @SerializedName("no_hp")
        private String nomorHp;

        @SerializedName("photo")
        private String photo;

        public String getNamaBengkel() {
            return namaBengkel;
        }

        public String getAlamat() {
            return alamat;
        }

        public String getNomorHp() {
            return nomorHp;
        }

        public String getPhoto() {
            return photo;
        }
    }
}

