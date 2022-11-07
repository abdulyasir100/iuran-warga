package individual;

import java.util.Date;

public class Chat {
    private Warga pembalas;
    private Warga penerima;
    private int detik;
    private int menit;
    private int jam;
    private int bulan;
    private int hari;
    private int tahun;
    private String chat;

    public Chat(Warga pembalas, Warga penerima, Date date, String chat) {
        this.pembalas = pembalas;
        this.penerima = penerima;
        this.detik = date.getSeconds();
        this.menit = date.getMinutes();
        this.jam = date.getHours();
        this.bulan = date.getMonth();
        this.hari = date.getDay();
        this.tahun = date.getYear();
        this.chat = chat;
    }

    public Warga getPembalas() {
        return pembalas;
    }

    public void setPembalas(Warga pembalas) {
        this.pembalas = pembalas;
    }

    public Warga getPenerima() {
        return penerima;
    }

    public void setPenerima(Warga penerima) {
        this.penerima = penerima;
    }

    public int getDetik() {
        return detik;
    }

    public void setDetik(int detik) {
        this.detik = detik;
    }

    public int getMenit() {
        return menit;
    }

    public void setMenit(int menit) {
        this.menit = menit;
    }

    public int getJam() {
        return jam;
    }

    public void setJam(int jam) {
        this.jam = jam;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public int getHari() {
        return hari;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
