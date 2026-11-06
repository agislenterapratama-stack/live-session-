package model;

import interfaces.CetakTiket;

// Subclass 2: Motor mewarisi Kendaraan (extends) dan memenuhi
// kontrak interface CetakTiket (implements).
public class Motor extends Kendaraan implements CetakTiket {

    public static final double TARIF_PER_JAM = 2000;

    private int cc;
    private boolean matic;

    public Motor(String platNomor, String pemilik, int jamMasuk,
                 int cc, boolean matic) {
        super(platNomor, pemilik, jamMasuk); // memanggil constructor superclass
        setCc(cc);
        setMatic(matic);
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        if (cc < 50) {
            throw new IllegalArgumentException("CC motor minimal 50.");
        }
        this.cc = cc;
    }

    public boolean isMatic() {
        return matic;
    }

    public void setMatic(boolean matic) {
        this.matic = matic;
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
        return "Motor";
    }

    // Overriding: infoDasar() ditambah detail khusus Motor.
    @Override
    public String infoDasar() {
        return super.infoDasar()
                + " | CC: " + cc
                + " | Transmisi: " + (matic ? "Matic" : "Manual");
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
