package Koperasi;

public class Kendaraan {
    private String platNomor;
    private String jenis;
    private double tarif12Jam; // Tarif untuk setengah hari
    private double tarif1Hari; // Tarif untuk 24 jam

    public Kendaraan(String platNomor, String jenis, double tarif12Jam, double tarif1Hari) {
        this.platNomor = platNomor;
        this.jenis = jenis;
        this.tarif12Jam = tarif12Jam;
        this.tarif1Hari = tarif1Hari;
    }

    public String getPlatNomor() { return platNomor; }
    public String getJenis() { return jenis; }
    public double getTarif12Jam() { return tarif12Jam; }
    public double getTarif1Hari() { return tarif1Hari; }

    public String getInfoKendaraan() {
        return platNomor + " [" + jenis + "] - 12 Jam: Rp" + tarif12Jam + " | 1 Hari: Rp" + tarif1Hari;
    }
}