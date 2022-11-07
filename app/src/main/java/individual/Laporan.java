package individual;

public class Laporan {
    private Warga pelapor;
    private String laporan;

    public Laporan(Warga pelapor, String laporan) {
        this.pelapor = pelapor;
        this.laporan = laporan;
    }

    public Warga getPelapor() {
        return pelapor;
    }

    public void setPelapor(Warga pelapor) {
        this.pelapor = pelapor;
    }

    public String getLaporan() {
        return laporan;
    }

    public void setLaporan(String laporan) {
        this.laporan = laporan;
    }
}
