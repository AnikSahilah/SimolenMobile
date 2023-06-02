package com.example.landingpageactivity.ModelResponse.Montir;
public class PemesananMontir {
    private boolean status;
    private String message;
    private DataModel data;

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

    public DataModel getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }


    public class DataModel {
        private int id;
        private String total;
        private String tanggal_pemesanan;
        private String status;
        private CustomerModel customer;
        private MontirModel montir;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTanggal_pemesanan() {
            return tanggal_pemesanan;
        }

        public void setTanggal_pemesanan(String tanggal_pemesanan) {
            this.tanggal_pemesanan = tanggal_pemesanan;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public CustomerModel getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerModel customer) {
            this.customer = customer;
        }

        public MontirModel getMontir() {
            return montir;
        }

        public void setMontir(MontirModel montir) {
            this.montir = montir;
        }
    }

    public class CustomerModel {
        private int id;
        private String nama;
        private String alamat;
        private String no_hp;
        private String jenis_kelamin;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
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

        public String getJenis_kelamin() {
            return jenis_kelamin;
        }

        public void setJenis_kelamin(String jenis_kelamin) {
            this.jenis_kelamin = jenis_kelamin;
        }
    }

    public class MontirModel {
        private int id;
        private String nama;
        private String alamat;
        private String no_hp;
        private String jenis_kelamin;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
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

        public String getJenis_kelamin() {
            return jenis_kelamin;
        }

        public void setJenis_kelamin(String jenis_kelamin) {
            this.jenis_kelamin = jenis_kelamin;
        }
    }
}
