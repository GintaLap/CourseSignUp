import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Window.Type;
import javax.swing.UIManager;

public class RegistracijasLogs {
        private JFrame frame;
        private JTextField vards;
        private JTextField talrunis;
        private JTextField epasts;
        private JTextField textField_3;

        public static void main(String[] args) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        RegistracijasLogs window = new RegistracijasLogs();
                        window.frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        public RegistracijasLogs() {

            initialize();
        }
        private void initialize() {
            frame = new JFrame("Pieteikuma forma");
            frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
            frame.setType(Type.POPUP);
            frame.setForeground(Color.LIGHT_GRAY);
            frame.setFont(new Font("Arial", Font.BOLD, 12));
            frame.getContentPane().setBackground(SystemColor.control);
            frame.setBackground(SystemColor.desktop);
            frame.setBounds(600, 250, 450, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(null);

            JLabel lblNewLabel = new JLabel("Vārds");
            lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            lblNewLabel.setBounds(10, 31, 155, 42);
            frame.getContentPane().add(lblNewLabel);

            vards = new JTextField();
            vards.setFont(new Font("Arial", Font.PLAIN, 16));
            vards.setBounds(200, 33, 226, 42);
            frame.getContentPane().add(vards);
            vards.setColumns(10);

            JLabel lblNewLabel_1 = new JLabel("Tālrunis");
            lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
            lblNewLabel_1.setBounds(10, 83, 155, 42);
            frame.getContentPane().add(lblNewLabel_1);


            JLabel lblNewLabel_2 = new JLabel("E-Pasts");
            lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
            lblNewLabel_2.setBounds(10, 135, 163, 42);
            frame.getContentPane().add(lblNewLabel_2);

            talrunis = new JTextField();
            talrunis.setFont(new Font("Arial", Font.PLAIN, 16));
            talrunis.setColumns(10);
            talrunis.setBounds(200, 85, 226, 42);
            frame.getContentPane().add(talrunis);

            epasts = new JTextField();
            epasts.setFont(new Font("Arial", Font.PLAIN, 16));
            epasts.setColumns(10);
            epasts.setBounds(200, 137, 226, 42);
            frame.getContentPane().add(epasts);

            JLabel lblNewLabel_3 = new JLabel("Mācību programma");
            lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 20));
            lblNewLabel_3.setBounds(10, 187, 201, 42);
            frame.getContentPane().add(lblNewLabel_3);

            String[] programma = new String[] {"JAVA Programmēšana ", "PYTHON Programmēšana", "JAVASCRIPT Programmēšana"};


            JComboBox<?> comboBox = new JComboBox<Object>(programma);
            comboBox.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
            comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
            comboBox.setBounds(200, 188, 226, 42);
            frame.getContentPane().add(comboBox);

            JButton btnNewButton = new JButton("Iesniegt");
            btnNewButton.setBackground(UIManager.getColor("Button.shadow"));
            btnNewButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent arg0) {

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/pieteikumi_kursiem?serverTimezone=UTC", "root", "Darynda123");
                        System.out.println("Connection is made!");
                        String strInsert = "insert into pieteikumi (vards, talrunis, epasts, programma) values (?, ?,?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(strInsert);
                        if (isNumurs(2, talrunis.getText())) {
                            textField_3.setText("Paldies! Pieteikums ir saņemts!");
                            stmt.setString(1, vards.getText());
                            stmt.setString (2, talrunis.getText());
                            stmt.setString(3, epasts.getText());
                            String programma;
                            programma = comboBox.getSelectedItem().toString();
                            stmt.setString(4, programma);
                            stmt.executeUpdate();

                        }
                           else {
                            textField_3.setForeground(Color.RED);
                            textField_3.setText("Tālruņa numurs ievadīts nepareizi!");
                            conn.close();
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Connection failed!");
                    }
                }
            });


            btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
            btnNewButton.setBounds(200, 239, 226, 45);
            frame.getContentPane().add(btnNewButton);

            textField_3 = new JTextField();
            textField_3.setHorizontalAlignment(SwingConstants.LEFT);
            textField_3.setFont(new Font("Arial", Font.PLAIN, 16));
            textField_3.setColumns(10);
            textField_3.setBounds(10, 294, 416, 59);
            frame.getContentPane().add(textField_3);
        }

        static boolean isNumurs(int i, String str) {
            int i1;
            try {
                i1 = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return false;
            }
            byte count = 0;
            while (i1 > 0) {
                i1 = i1 / 10;
                count++;
            }
            if (count == 8) return true;
            else return false;
        }
    }
