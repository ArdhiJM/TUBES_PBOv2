package Koperasi;

public class Peminjam extends Orang {
    private String idPeminjam;
    // Variabel static milik kelas untuk membuat nomor urut otomatis
    private static int counter = 1; 

    public Peminjam(String nama, String alamat) {
        super(nama, alamat);
        // ID digenerate otomatis, contoh: PMJ-001, PMJ-002, dst.
        this.idPeminjam = String.format("PMJ-%03d", counter++);
    }

    public String getIdPeminjam() {
        return idPeminjam;
    }
    
    public String getInfoPeminjam() {
        return idPeminjam + " - " + getNama();
    }
}