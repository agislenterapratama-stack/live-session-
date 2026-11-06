package model;

// Abstract class (superclass): tidak bisa dibuat objeknya langsung.
// Menyimpan data umum semua kendaraan dan mewajibkan subclass
// mengimplementasikan hitungBiaya() dan getJenis().
public abstract class Kendaraan {

    // Encapsulation: atribut private, hanya bisa diakses lewat getter/setter.
    private String platNomor;
    private String pemilik;
    private int jamMasuk; // 0-23

    public Kendaraan(String platNomor, String pemilik, int jamMasuk) {
        setPlatNomor(platNomor);
        setPemilik(pemilik);
        setJamMasuk(jamMasuk);
    }

    // Abstract method: wajib di-override oleh Mobil dan Motor.
    public abstract double hitungBiaya(int jamParkir);

    public abstract String getJenis();

    // Getter dan setter (encapsulation).
    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        if (platNomor == null || platNomor.isBlank()) {
            throw new IllegalArgumentException("Plat nomor tidak boleh kosong.");
        }
        this.platNomor = platNomor.trim().toUpperCase();
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        if (pemilik == null || pemilik.isBlank()) {
            throw new IllegalArgumentException("Nama pemilik tidak boleh kosong.");
        }
        this.pemilik = pemilik.trim();
    }

    public int getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(int jamMasuk) {
        if (jamMasuk < 0 || jamMasuk > 23) {
            throw new IllegalArgumentException("Jam masuk harus 0-23.");
        }
        this.jamMasuk = jamMasuk;
    }

    // Method konkret yang dipakai bersama oleh semua subclass.
    public String infoDasar() {
        return getJenis() + " | Plat: " + platNomor
                + " | Pemilik: " + pemilik
                + " | Masuk jam " + jamMasuk;
    }

    // Overloading: info() dan info(boolean) namanya sama, parameternya beda.
    public String info() {
        return infoDasar();
    }

    public String info(boolean lengkap) {
        if (lengkap) {
            return infoDasar() + " | Tarif/jam: Rp" + (long) hitungBiaya(1);
        }
        return infoDasar();
    }

    @Override
    public String toString() {
        return infoDasar();
    }
}
