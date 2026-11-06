package interfaces;

// Interface: kontrak yang wajib dipenuhi setiap class yang mengimplementasikannya.
// Setiap kendaraan yang bisa dicetak tiketnya wajib menyediakan cetakTiket().
public interface CetakTiket {
    void cetakTiket(int jamParkir);
}
