package com.example.landingpageactivity.ModelResponse.Barang;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PembelianResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private PembelianData data;
    // Getter methods

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public PembelianData getData() {
        return data;
    }

    public class PembelianData {
        @SerializedName("id")
        private int id;

        @SerializedName("jumlah")
        private int jumlah;

        @SerializedName("total")
        private int total;

        @SerializedName("status")
        private String status;

        @SerializedName("tanggal_pembelian")
        private String tanggalPembelian;

        @SerializedName("customer")
        private CustomerData customer;

        @SerializedName("barang")
        private BarangData barang;

        // Getter methods

        public int getId() {
            return id;
        }

        public int getJumlah() {
            return jumlah;
        }

        public int getTotal() {
            return total;
        }

        public String getStatus() {
            return status;
        }

        public String getTanggalPembelian() {
            return tanggalPembelian;
        }

        public CustomerData getCustomer() {
            return customer;
        }

        public BarangData getBarang() {
            return barang;
        }
    }

    public class CustomerData {
        @SerializedName("nama")
        private String nama;

        @SerializedName("alamat")
        private String alamat;

        @SerializedName("no_hp")
        private String noHp;

        @SerializedName("jenis_kelamin")
        private String jenisKelamin;

        // Getter methods

        public String getNama() {
            return nama;
        }

        public String getAlamat() {
            return alamat;
        }

        public String getNoHp() {
            return noHp;
        }

        public String getJenisKelamin() {
            return jenisKelamin;
        }
    }

    public class BarangData {
        @SerializedName("id")
        private int id;

        @SerializedName("id_bengkel")
        private String idBengkel;

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

        // Getter methods

        public int getId() {
            return id;
        }

        public String getIdBengkel() {
            return idBengkel;
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
    }

}
