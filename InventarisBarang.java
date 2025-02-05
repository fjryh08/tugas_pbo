import java.sql.*;
import java.util.Scanner;

public class InventarisBarang {
    private static Connection conn = Koneksi.getKoneksi();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== MENU INVENTARIS BARANG ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Barang");
            System.out.println("3. Update Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); // Membuang newline

           switch (pilihan) {
               case 1:
                   tambahBarang();
                   break;
               case 2:
                   tampilkanBarang();
                   break;
               case 3:
                   updateBarang();
                   break;
               case 4:
                   hapusBarang();
                   break;
               case 5:
                   System.out.println("Keluar dari program...");
                   break;
               default:
                   System.out.println("Pilihan tidak valid!");
                   break;
           }
        } while (pilihan != 5);
    }

    public static void tambahBarang() {
        try {
            System.out.print("Masukkan Nama Barang: ");
            String nama = input.nextLine();
            System.out.print("Masukkan Kategori: ");
            String kategori = input.nextLine();
            System.out.print("Masukkan Harga: ");
            double harga = input.nextDouble();
            System.out.print("Masukkan Jumlah: ");
            int jumlah = input.nextInt();

            String sql = "INSERT INTO tb_barang (nama_barang, kategori, harga, jumlah) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, kategori);
            pst.setDouble(3, harga);
            pst.setInt(4, jumlah);
            pst.executeUpdate();
            System.out.println("Data barang berhasil ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan barang: " + e.getMessage());
        }
    }

    public static void tampilkanBarang() {
        try {
            String sql = "SELECT * FROM tb_barang";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n=== DAFTAR BARANG ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idbarang") +
                        " | Nama: " + rs.getString("nama_barang") +
                        " | Kategori: " + rs.getString("kategori") +
                        " | Harga: " + rs.getDouble("harga") +
                        " | Jumlah: " + rs.getInt("jumlah"));
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
    }

    public static void updateBarang() {
        try {
            System.out.print("Masukkan ID Barang yang akan diupdate: ");
            int id = input.nextInt();
            input.nextLine(); // Membersihkan newline

            System.out.print("Masukkan Nama Barang Baru: ");
            String nama = input.nextLine();
            System.out.print("Masukkan Kategori Baru: ");
            String kategori = input.nextLine();
            System.out.print("Masukkan Harga Baru: ");
            double harga = input.nextDouble();
            System.out.print("Masukkan Jumlah Baru: ");
            int jumlah = input.nextInt();

            String sql = "UPDATE tb_barang SET nama_barang=?, kategori=?, harga=?, jumlah=? WHERE idbarang=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, kategori);
            pst.setDouble(3, harga);
            pst.setInt(4, jumlah);
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Data barang berhasil diperbarui!");
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate data: " + e.getMessage());
        }
    }

    public static void hapusBarang() {
        try {
            System.out.print("Masukkan ID Barang yang akan dihapus: ");
            int id = input.nextInt();

            String sql = "DELETE FROM tb_barang WHERE idbarang=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Data barang berhasil dihapus!");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }
}