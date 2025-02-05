import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;

    public static Connection getKoneksi() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/uas_pbo"; // Sesuaikan dengan nama database Anda
                String user = "root"; // Sesuaikan dengan user MySQL Anda
                String password = ""; // Sesuaikan dengan password MySQL Anda (kosong jika default)
                
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi Berhasil!");
            } catch (SQLException e) {
                System.out.println("Koneksi Gagal: " + e.getMessage());
            }
        }
        return conn;
    }
}
