package Koperasi;

public class Orang {
    // Menggunakan protected agar sub-class bisa mengakses secara langsung (Materi Modul 8)
    protected String nama;
    protected String alamat;

    // Konstruktor Super-class
    public Orang(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    // Encapsulation: Getter untuk keamanan data (Materi Modul 4 & 5)
    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }
}