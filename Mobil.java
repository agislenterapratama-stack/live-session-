package model;

import interfaces.CetakTiket;

// Subclass 1: Mobil mewarisi Kendaraan (extends) dan memenuhi
// kontrak interface CetakTiket (implements).
public class Mobil extends Kendaraan implements CetakTiket {

    public static final double TARIF_PER_JAM = 5000;

    private int jumlahKursi;
    private String jenisMobil; // contoh: MPV, SUV, Sedan

    public Mobil(String platNomor, String pemilik, int jamMasuk,
                 int jumlahKursi, String jenisMobil) {
        super(platNomor, pemilik, jamMasuk); // memanggil constructor superclass
        setJumlahKursi(jumlahKursi);
        setJenisMobil(jenisMobil);
    }

    public int getJumlahKursi() {
        return jumlahKursi;
    }

    public void setJumlahKursi(int jumlahKursi) {
        if (jumlahKursi < 1) {
            throw new IllegalArgumentException("Jumlah kursi minimal 1.");
        }
        this.jumlahKursi = jumlahKursi;
    }

    public String getJenisMobil() {
        return jenisMobil;
    }

    public void setJenisMobil(String jenisMobil) {
        if (jenisMobil == null || jenisMobil.isBlank()) {
            throw new IllegalArgumentException("Jenis mobil tidak boleh kosong.");
        }
        this.jenisMobil = jenisMobil.trim();
    }

    // Overriding: isi method abstract dari Kendaraan.
    @Override
    public double hitungBiaya(int jamParkir) {
        if (jamParkir <= 0) {
            return 0;
        }
        return TARIF_PER_JAM * jamParkir;
    }

    @Override
    public String getJenis() {
        return "Mobil";
    }

    // Overriding: infoDasar() ditambah detail khusus Mobil.
    @Override
    public String infoDasar() {
        return super.infoDasar()
                + " | Jenis: " + jenisMobil
                + " | Kursi: " + jumlahKursi;
    }

    // Memenuhi kontrak interface CetakTiket.
    @Override
    public void cetakTiket(int jamParkir) {
        System.out.println("===== TIKET PARKIR =====");
        System.out.println(infoDasar());
        System.out.println("Lama parkir : " + jamParkir + " jam");
        System.out.println("Tarif/jam   : Rp" + (long) TARIF_PER_JAM);
        System.out.println("Total       : Rp" + (long) hitungBiaya(jamParkir));
        System.out.println("=========================");
    }
}
