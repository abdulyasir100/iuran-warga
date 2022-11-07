package individual;

public class Komentar {
    private Status status;
    private Warga komen;
    private String komentar;

    public Komentar(Status status, Warga komen, String komentar) {
        this.status = status;
        this.komen = komen;
        this.komentar = komentar;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Warga getKomen() {
        return komen;
    }

    public void setKomen(Warga komen) {
        this.komen = komen;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
