package Koperasi;

public class Transaksi implements Layanan {
    private Peminjam penyewa;
    private Kendaraan armada;
    private String paketSewa; // Berisi teks "Paket 12 Jam" atau "Paket 1 Hari"

    public Transaksi(Peminjam penyewa, Kendaraan armada, String paketSewa) {
        this.penyewa = penyewa;
        this.armada = armada;
        this.paketSewa = paketSewa;
    }

    @Override
    public double hitungTotalBiaya() {
        // Logika pemilihan tarif berdasarkan paket yang dipilih
        if (paketSewa.equals("Paket 12 Jam")) {
            return armada.getTarif12Jam();
        } else {
            return armada.getTarif1Hari();
        }
    }

    public String getDetailTransaksi() {
        return "Peminjam: " + penyewa.getNama() + 
               " | Armada: " + armada.getPlatNomor() + 
               " | Durasi: " + paketSewa +
               " | Total: Rp " + hitungTotalBiaya();
    }
}