package individual;

public class Petinggi {
    private Warga ketuaRt;
    private Warga bendahara;
    private Warga sekretaris;

    public Petinggi(Warga ketuaRt, Warga bendahara, Warga sekretaris) {
        this.ketuaRt = ketuaRt;
        this.bendahara = bendahara;
        this.sekretaris = sekretaris;
    }

    public Warga getKetuaRt() {
        return ketuaRt;
    }

    public void setKetuaRt(Warga ketuaRt) {
        this.ketuaRt = ketuaRt;
    }

    public Warga getBendahara() {
        return bendahara;
    }

    public void setBendahara(Warga bendahara) {
        this.bendahara = bendahara;
    }

    public Warga getSekretaris() {
        return sekretaris;
    }

    public void setSekretaris(Warga sekretaris) {
        this.sekretaris = sekretaris;
    }
}
