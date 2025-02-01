import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KlikSaya {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplikasi GUI Sederhana");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); 

        
        JButton button = new JButton("Klik Saya");
        button.setBounds(100, 50, 100, 30); 

        
        final JLabel label = new JLabel("");
        label.setBounds(90, 100, 200, 30); 

        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Tombol telah diklik!");
            }
        });

        
        frame.add(button);
        frame.add(label);

        
        frame.setVisible(true);
    }