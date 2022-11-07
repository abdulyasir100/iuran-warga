package individual;

import java.util.Date;

public class Iuran {
    private double totalIuran;
    private int bulan;
    private int tanggal;
    private int tahun;
    private Warga pembayarIuran;
    private boolean sudahBayar;

    public Iuran(){}

    public Iuran(double totalIuran, Date date, Warga pembayarIuran, boolean sudahBayar) {
        this.totalIuran = totalIuran;
        this.bulan = date.getMonth();
        this.tanggal = date.getDay();
        this.tahun = date.getYear();
        this.pembayarIuran = pembayarIuran;
        this.sudahBayar = sudahBayar;
    }

    public double getTotalIuran() {
        return totalIuran;
    }

    public void setTotalIuran(double totalIuran) {
        this.totalIuran = totalIuran;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public int getTanggal() {
        return tanggal;
    }

    public void setTanggal(int tanggal) {
        this.tanggal = tanggal;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public Warga getPembayarIuran() {
        return pembayarIuran;
    }

    public void setPembayarIuran(Warga pembayarIuran) {
        this.pembayarIuran = pembayarIuran;
    }

    public boolean isSudahBayar() {
        return sudahBayar;
    }

    public void setSudahBayar(boolean sudahBayar) {
        this.sudahBayar = sudahBayar;
    }
}
