package com.example.landingpageactivity.ModelResponse.Bengkel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.List;

public class BengkelResponse {
    private boolean status;
    private String message;
    private List<PemesananBengkelData> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PemesananBengkelData> getData() {
        return data;
    }

    public void setData(List<PemesananBengkelData> data) {
        this.data = data;
    }

    public class PemesananBengkelData {
        private int id;
        private String nama_bengkel;
        private String alamat;
        private String no_hp;
        private String photo;
        private List<MontirData> montir;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNama_bengkel() {
            return nama_bengkel;
        }

        public void setNama_bengkel(String nama_bengkel) {
            this.nama_bengkel = nama_bengkel;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getNo_hp() {
            return no_hp;
        }

        public void setNo_hp(String no_hp) {
            this.no_hp = no_hp;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public List<MontirData> getMontir() {
            return montir;
        }

        public void setMontir(List<MontirData> montir) {
            this.montir = montir;
        }
    }

    public class MontirData implements Serializable {
        private int id;
        private String name;
        private String jenis_kelamin;
        private String status;
        private String photo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJenis_kelamin() {
            return jenis_kelamin;
        }

        public void setJenis_kelamin(String jenis_kelamin) {
            this.jenis_kelamin = jenis_kelamin;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}


