package individual;

public class Lokasi {
    private String alamat;
    private String kode_lokasi;

    public Lokasi(){}

    public Lokasi(String alamat) {
        this.alamat = alamat;
        this.kode_lokasi = generateKode();
    }

    private String generateKode(){
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        int counter = 10;
        while(counter-- != 0){
            int c = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(c));
        }
        return builder.toString();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKode_lokasi() {
        return kode_lokasi;
    }

    public void setKode_lokasi(String kode_lokasi) {
        this.kode_lokasi = kode_lokasi;
    }
}
