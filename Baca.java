import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Baca  extends JFrame {
    private JButton bacaFileButton;
    private JTextArea textArea;

    public Baca() {
        // Mengatur judul frame
        setTitle("Aplikasi Baca File");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat tombol
        bacaFileButton = new JButton("Baca File");
        bacaFileButton.setBackground(Color.decode("#D8A7D0")); // Warna hijau terang
        bacaFileButton.setForeground(Color.BLACK);             // Teks warna hitam
        bacaFileButton.setFont(new Font("Courier New", Font.BOLD, 16)); // Font tebal

        // Membuat area teks
        textArea = new JTextArea();
        textArea.setEditable(false); // Area teks hanya untuk membaca
        textArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Font teks
        JScrollPane scrollPane = new JScrollPane(textArea); // Scroll jika teks panjang

        // Menambahkan event listener ke tombol
        bacaFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bacaFile();
            }
        });

        // Mengatur layout
        setLayout(new BorderLayout());
        add(bacaFileButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void bacaFile() {
        // Memunculkan dialog untuk memilih file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                textArea.setText(""); // Mengosongkan area teks
                String line;
                while ((line = br.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error membaca file: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada file yang dipilih!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Menjalankan aplikasi
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Baca().setVisible(true);
            }
        });
    }
}