package Koperasi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainKoperasi extends JFrame implements ActionListener {
    
    private ArrayList<Peminjam> daftarPeminjam = new ArrayList<>();
    private ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
    private ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

    // Komponen GUI Layout
    private JTextField txtNamaPeminjam, txtAlamatPeminjam;
    private JTextField txtPlat, txtJenis, txtTarif12, txtTarif24;
    private JTextField txtCariNama, txtCariPlat;
    private JComboBox<String> cmbPaketSewa; // Komponen Dropdown Baru!
    private JTextArea areaOutput;
    private JButton btnTambahPeminjam, btnTambahKendaraan, btnProsesTransaksi;

    public MainKoperasi() {
        setTitle("Aplikasi Koperasi Rental - Tugas Besar PBO");
        setSize(900, 650); // Ukuran diperlebar sedikit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL INPUT DATA (KIRI) ---
        JPanel panelKiri = new JPanel();
        panelKiri.setLayout(new BoxLayout(panelKiri, BoxLayout.Y_AXIS));
        panelKiri.setBorder(BorderFactory.createTitledBorder("Form Input Koperasi"));

        // Bagian 1: Input Peminjam
        panelKiri.add(new JLabel("=== DAFTAR PEMINJAM BARU ==="));
        txtNamaPeminjam = new JTextField(10);
        txtAlamatPeminjam = new JTextField(10);
        panelKiri.add(new JLabel("Nama Lengkap:")); panelKiri.add(txtNamaPeminjam);
        panelKiri.add(new JLabel("Alamat:")); panelKiri.add(txtAlamatPeminjam);
        btnTambahPeminjam = new JButton("Simpan Peminjam");
        btnTambahPeminjam.addActionListener(this); 
        panelKiri.add(btnTambahPeminjam);
        panelKiri.add(Box.createVerticalStrut(15));

        // Bagian 2: Input Kendaraan
        panelKiri.add(new JLabel("=== INPUT ARMADA KENDARAAN ==="));
        txtPlat = new JTextField(10);
        txtJenis = new JTextField(10);
        txtTarif12 = new JTextField(10);
        txtTarif24 = new JTextField(10);
        panelKiri.add(new JLabel("Plat Nomor:")); panelKiri.add(txtPlat);
        panelKiri.add(new JLabel("Jenis (Mobil/Motor):")); panelKiri.add(txtJenis);
        panelKiri.add(new JLabel("Tarif 12 Jam (Rp):")); panelKiri.add(txtTarif12);
        panelKiri.add(new JLabel("Tarif 1 Hari (Rp):")); panelKiri.add(txtTarif24);
        btnTambahKendaraan = new JButton("Simpan Kendaraan");
        btnTambahKendaraan.addActionListener(this); 
        panelKiri.add(btnTambahKendaraan);
        panelKiri.add(Box.createVerticalStrut(15));

        // Bagian 3: Input Transaksi (Menggunakan Nama dan Dropdown Paket)
        panelKiri.add(new JLabel("=== TRANSAKSI SEWA ==="));
        txtCariNama = new JTextField(10);
        txtCariPlat = new JTextField(10);
        
        // Membuat pilihan Dropdown untuk paket sewa
        String[] pilihanPaket = {"Paket 12 Jam", "Paket 1 Hari"};
        cmbPaketSewa = new JComboBox<>(pilihanPaket);

        panelKiri.add(new JLabel("Ketik Nama Peminjam:")); panelKiri.add(txtCariNama);
        panelKiri.add(new JLabel("Ketik Plat Nomor:")); panelKiri.add(txtCariPlat);
        panelKiri.add(new JLabel("Pilih Paket Sewa:")); panelKiri.add(cmbPaketSewa);
        
        btnProsesTransaksi = new JButton("Hitung & Cetak Transaksi");
        btnProsesTransaksi.addActionListener(this); 
        panelKiri.add(btnProsesTransaksi);

        add(panelKiri, BorderLayout.WEST);

        // --- PANEL OUTPUT MONITOR (KANAN/CENTER) ---
        JPanel panelKanan = new JPanel(new BorderLayout());
        panelKanan.setBorder(BorderFactory.createTitledBorder("Console & Nota Monitor"));
        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        areaOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaOutput);
        panelKanan.add(scroll, BorderLayout.CENTER);

        add(panelKanan, BorderLayout.CENTER);

        // Data Awal Dummy
        daftarPeminjam.add(new Peminjam("Adsu", "Bandung")); 
        daftarKendaraan.add(new Kendaraan("D 1234 ABC", "Mobil Avanza", 150000, 250000));
        refreshMonitor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTambahPeminjam) {
            String nama = txtNamaPeminjam.getText();
            String alamat = txtAlamatPeminjam.getText();
            if(!nama.isEmpty()) {
                daftarPeminjam.add(new Peminjam(nama, alamat));
                JOptionPane.showMessageDialog(this, "Peminjam Berhasil Disimpan!");
                txtNamaPeminjam.setText(""); txtAlamatPeminjam.setText("");
                refreshMonitor();
            }
        } 
        else if (e.getSource() == btnTambahKendaraan) {
            String plat = txtPlat.getText();
            String jenis = txtJenis.getText();
            String tarif12Str = txtTarif12.getText();
            String tarif24Str = txtTarif24.getText();
            
            if(!plat.isEmpty() && !tarif12Str.isEmpty() && !tarif24Str.isEmpty()) {
                double t12 = Double.parseDouble(tarif12Str);
                double t24 = Double.parseDouble(tarif24Str);
                daftarKendaraan.add(new Kendaraan(plat, jenis, t12, t24));
                JOptionPane.showMessageDialog(this, "Kendaraan Berhasil Disimpan!");
                txtPlat.setText(""); txtJenis.setText(""); txtTarif12.setText(""); txtTarif24.setText("");
                refreshMonitor();
            }
        } 
        else if (e.getSource() == btnProsesTransaksi) {
            String namaCari = txtCariNama.getText();
            String platCari = txtCariPlat.getText();
            // Mengambil nilai teks dari dropdown yang dipilih user
            String paketDipilih = (String) cmbPaketSewa.getSelectedItem();

            Peminjam peminjamTerpilih = null;
            Kendaraan kendaraanTerpilih = null;

            // Pencarian Peminjam BERDASARKAN NAMA (Mengabaikan huruf besar/kecil)
            for (Peminjam p : daftarPeminjam) {
                if (p.getNama().equalsIgnoreCase(namaCari)) {
                    peminjamTerpilih = p;
                    break;
                }
            }
            
            // Pencarian Kendaraan berdasarkan Plat
            for (Kendaraan k : daftarKendaraan) {
                if (k.getPlatNomor().equalsIgnoreCase(platCari)) {
                    kendaraanTerpilih = k;
                    break;
                }
            }

            if (peminjamTerpilih != null && kendaraanTerpilih != null) {
                Transaksi t = new Transaksi(peminjamTerpilih, kendaraanTerpilih, paketDipilih);
                daftarTransaksi.add(t);
                JOptionPane.showMessageDialog(this, "Transaksi Sukses Disimpan!");
                txtCariNama.setText(""); txtCariPlat.setText("");
                refreshMonitor();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Nama Peminjam atau Plat Nomor tidak ditemukan!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshMonitor() {
        areaOutput.setText("");
        areaOutput.append("========================================================\n");
        areaOutput.append("               SISTEM INFORMASI KOPERASI RENTAL         \n");
        areaOutput.append("========================================================\n\n");

        areaOutput.append("[ DAFTAR PEMINJAM (Gunakan NAMA untuk Transaksi) ]\n");
        for (Peminjam p : daftarPeminjam) {
            areaOutput.append("- " + p.getNama() + " (" + p.getAlamat() + ")\n");
        }

        areaOutput.append("\n[ DAFTAR ARMADA KENDARAAN TERSEDIA ]\n");
        for (Kendaraan k : daftarKendaraan) {
            areaOutput.append("- " + k.getInfoKendaraan() + "\n");
        }

        areaOutput.append("\n[ LOG TRANSAKSI LAYANAN SEWA ]\n");
        for (Transaksi t : daftarTransaksi) {
            areaOutput.append("> " + t.getDetailTransaksi() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainKoperasi().setVisible(true);
        });
    }
}