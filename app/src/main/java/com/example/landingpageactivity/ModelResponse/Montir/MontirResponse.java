package com.example.landingpageactivity.ModelResponse.Montir;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MontirResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<MontirData> data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<MontirData> getData() {
        return data;
    }

    public class MontirData {
        @SerializedName("id")
        private int id;

        @SerializedName("nama")
        private String nama;

        @SerializedName("alamat")
        private String alamat;

        @SerializedName("no_hp")
        private String noHp;

        @SerializedName("jenis_kelamin")
        private String jenisKelamin;

        @SerializedName("status")
        private String status;

        @SerializedName("photo")
        private String photo;

        @SerializedName("bengkel")
        private BengkelData bengkel;

        public int getId() {
            return id;
        }

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

        public String getStatus() {
            return status;
        }

        public String getPhoto() {
            return photo;
        }

        public BengkelData getBengkel() {
            return bengkel;
        }
    }

    public class BengkelData {
        @SerializedName("id")
        private int id;

        @SerializedName("nama_bengkel")
        private String namaBengkel;

        @SerializedName("alamat")
        private String alamat;

        @SerializedName("no_hp")
        private String noHp;

        @SerializedName("photo")
        private String photo;

        public int getId() {
            return id;
        }

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

