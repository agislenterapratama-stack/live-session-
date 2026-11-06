package service;

import interfaces.CetakTiket;
import java.util.ArrayList;
import java.util.List;
import model.Kendaraan;
import model.Mobil;
import model.Motor;

// Mengelola daftar kendaraan yang sedang parkir dan total pendapatan.
public class ParkirManager {

    private final List<Kendaraan> daftar = new ArrayList<>();
    private double totalPendapatan = 0;

    public void tambahKendaraan(Kendaraan kendaraan) {
        if (kendaraan == null) {
            throw new IllegalArgumentException("Kendaraan tidak boleh null.");
        }
        daftar.add(kendaraan);
        System.out.println("Kendaraan masuk: " + kendaraan.infoDasar());
    }

    // Overloading: tambahKendaraan yang sama namanya, parameternya beda.
    // Versi ini membuat objek Mobil/Motor dari data mentah (String).
    public void tambahKendaraan(String platNomor, String pemilik,
                                String jenis, int jamMasuk) {
        Kendaraan kendaraan;
        if (jenis.equalsIgnoreCase("Mobil")) {
            kendaraan = new Mobil(platNomor, pemilik, jamMasuk, 5, "MPV");
        } else if (jenis.equalsIgnoreCase("Motor")) {
            kendaraan = new Motor(platNomor, pemilik, jamMasuk, 150, true);
        } else {
            throw new IllegalArgumentException("Jenis harus Mobil atau Motor.");
        }
        tambahKendaraan(kendaraan);
    }

    // Overloading: hitungTotal yang sama namanya, parameternya beda.
    public double hitungTotal(Kendaraan kendaraan, int jamParkir) {
        return kendaraan.hitungBiaya(jamParkir);
    }

    public double hitungTotal(Kendaraan kendaraan, int jamParkir, boolean isMember) {
        double total = kendaraan.hitungBiaya(jamParkir);
        if (isMember) {
            total = total * 0.9; // diskon member 10%
        }
        return total;
    }

    public Kendaraan cariKendaraan(String platNomor) {
        for (Kendaraan kendaraan : daftar) {
            if (kendaraan.getPlatNomor().equalsIgnoreCase(platNomor)) {
                return kendaraan;
            }
        }
        return null;
    }

    public void tampilkanSemua() {
        if (daftar.isEmpty()) {
            System.out.println("Parkir kosong.");
            return;
        }
        // Runtime polymorphism: variabel kendaraan bertipe Kendaraan,
        // tetapi infoDasar() yang jalan adalah versi Mobil/Motor.
        for (Kendaraan kendaraan : daftar) {
            System.out.println("- " + kendaraan.infoDasar());
        }
    }

    public void prosesKeluar(String platNomor, int jamParkir, boolean isMember) {
        Kendaraan kendaraan = cariKendaraan(platNomor);
        if (kendaraan == null) {
            System.out.println("Kendaraan plat " + platNomor + " tidak ditemukan.");
            return;
        }
        double total = hitungTotal(kendaraan, jamParkir, isMember);
        totalPendapatan += total;
        if (kendaraan instanceof CetakTiket) {
            ((CetakTiket) kendaraan).cetakTiket(jamParkir);
        }
        if (isMember) {
            System.out.println("Diskon member 10%.");
        }
        System.out.println("Total bayar: Rp" + (long) total);
        System.out.println();
        daftar.remove(kendaraan);
    }

    public double getTotalPendapatan() {
        return totalPendapatan;
    }

    public int getJumlahKendaraan() {
        return daftar.size();
    }
}
