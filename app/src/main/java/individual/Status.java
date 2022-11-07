package individual;

import java.io.File;

public class Status {
    private Warga pembuatStatus;
    private String status;
    private int jumlah_like;
    private int jumlah_komen;

    public Status(Warga pembuatStatus, String status, int jumlah_like, int jumlah_komen) {
        this.pembuatStatus = pembuatStatus;
        this.status = status;
        this.jumlah_like = jumlah_like;
        this.jumlah_komen = jumlah_komen;
    }

    public Warga getPembuatStatus() {
        return pembuatStatus;
    }

    public void setPembuatStatus(Warga pembuatStatus) {
        this.pembuatStatus = pembuatStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJumlah_like() {
        return jumlah_like;
    }

    public void setJumlah_like(int jumlah_like) {
        this.jumlah_like = jumlah_like;
    }

    public int getJumlah_komen() {
        return jumlah_komen;
    }

    public void setJumlah_komen(int jumlah_komen) {
        this.jumlah_komen = jumlah_komen;
    }
}
