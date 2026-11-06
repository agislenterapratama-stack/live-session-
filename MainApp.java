package main;

import model.Kendaraan;
import model.Mobil;
import model.Motor;
import service.ParkirManager;

// Entry point program ParkirKu.
public class MainApp {

    public static void main(String[] args) {
        ParkirManager parkir = new ParkirManager();

        System.out.println("== SISTEM PARKIR KENDARAAN PARKIRKU ==");
        System.out.println();

        // 4 objek pertama: referensi superclass Kendaraan menunjuk
        // objek subclass (Mobil/Motor) -> runtime polymorphism.
        Kendaraan k1 = new Mobil("B 1234 ABC", "Agis", 8, 5, "MPV");
        Kendaraan k2 = new Mobil("B 5678 DEF", "Budi", 9, 7, "SUV");
        Kendaraan k3 = new Motor("AD 9012 GH", "Citra", 10, 150, true);
        Kendaraan k4 = new Motor("AD 3456 IJ", "Dewi", 11, 110, false);

        parkir.tambahKendaraan(k1);
        parkir.tambahKendaraan(k2);
        parkir.tambahKendaraan(k3);
        parkir.tambahKendaraan(k4);
        // Objek ke-5 lewat overloading tambahKendaraan(String, ...).
        parkir.tambahKendaraan("B 7777 ZZ", "Eko", "Motor", 12);
        System.out.println();

        System.out.println("== Daftar kendaraan yang sedang parkir ==");
        parkir.tampilkanSemua();
        System.out.println();

        // Demo overloading hitungTotal: tanpa member vs dengan member.
        System.out.println("== Demo overloading hitungTotal ==");
        System.out.println("Mobil B 1234 ABC, 3 jam (bukan member): Rp"
                + (long) parkir.hitungTotal(k1, 3));
        System.out.println("Mobil B 1234 ABC, 3 jam (member): Rp"
                + (long) parkir.hitungTotal(k1, 3, true));
        System.out.println("Motor AD 9012 GH, 2 jam (bukan member): Rp"
                + (long) parkir.hitungTotal(k3, 2));
        System.out.println();

        // Demo getter/setter (encapsulation).
        System.out.println("== Demo getter/setter ==");
        System.out.println("Pemilik k2 sebelum diubah: " + k2.getPemilik());
        k2.setPemilik("Budi Santoso");
        System.out.println("Pemilik k2 sesudah diubah: " + k2.getPemilik());
        System.out.println("Info lengkap k2: " + k2.info(true));
        System.out.println();

        // Kendaraan keluar: cetak tiket (interface) + total bayar.
        System.out.println("== Kendaraan keluar ==");
        parkir.prosesKeluar("B 1234 ABC", 3, true);
        parkir.prosesKeluar("AD 9012 GH", 2, false);

        System.out.println("== Ringkasan ==");
        System.out.println("Total pendapatan: Rp" + (long) parkir.getTotalPendapatan());
        System.out.println("Sisa kendaraan di parkir: " + parkir.getJumlahKendaraan());
        parkir.tampilkanSemua();
    }
}
