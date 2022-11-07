package individual;

import java.util.Date;

public class Warga{
    private String nama;
    private String email;
    private String password;
    private String nik;
    private String no_kk;
    private String no_telp;
    private int bulan_lahir;
    private int tanggal_lahir;
    private int tahun_lahir;
    private String tempat_lahir;
    private String alamat;
    private Lokasi lokasi;

    public Warga(){}

    public Warga(String nama, String email, String password, String nik, String no_kk,
                 String no_telp, Date tanggal_lahir, String tempat_lahir, String alamat, Lokasi lokasi) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.nik = nik;
        this.no_kk = no_kk;
        this.no_telp = no_telp;
        this.tanggal_lahir = tanggal_lahir.getDate();
        this.bulan_lahir = tanggal_lahir.getMonth();
        this.tahun_lahir = tanggal_lahir.getYear();
        this.tempat_lahir = tempat_lahir;
        this.alamat = alamat;
        this.lokasi = lokasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNo_kk() {
        return no_kk;
    }

    public void setNo_kk(String no_kk) {
        this.no_kk = no_kk;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public int getBulan_lahir() {
        return bulan_lahir;
    }

    public void setBulan_lahir(int bulan_lahir) {
        this.bulan_lahir = bulan_lahir;
    }

    public int getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(int tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public int getTahun_lahir() {
        return tahun_lahir;
    }

    public void setTahun_lahir(int tahun_lahir) {
        this.tahun_lahir = tahun_lahir;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Lokasi getLokasi() {
        return lokasi;
    }

    public void setLokasi(Lokasi lokasi) {
        this.lokasi = lokasi;
    }
}
