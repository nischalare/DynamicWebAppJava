import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class UserRegistrationForm extends JFrame {
    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField emailField;

    public UserRegistrationForm() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Documents\\UserVectorImg.jpg"));
        setTitle("User Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(501, 520);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        nameField = new JTextField();
        nameField.setBounds(232, 50, 96, 19);
        getContentPane().add(nameField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        nameLabel.setBounds(87, 56, 85, 13);
        getContentPane().add(nameLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        passwordLabel.setBounds(87, 121, 96, 13);
        getContentPane().add(passwordLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        emailLabel.setBounds(87, 192, 85, 19);
        getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(232, 192, 96, 19);
        getContentPane().add(emailField);

        JButton submitButton = new JButton("Submit");
        submitButton.setForeground(new Color(128, 128, 64));
        submitButton.setBackground(new Color(255, 255, 128));
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        submitButton.setBounds(232, 314, 96, 32);
        getContentPane().add(submitButton);

        passwordField = new JPasswordField();
        passwordField.setBounds(232, 120, 96, 19);
        getContentPane().add(passwordField);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();

                Connection conn = connect();
                if (conn != null) {
                    try {
                        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        pstmt.setString(1, name);
                        pstmt.setString(2, password);
                        pstmt.setString(3, email);
                        pstmt.executeUpdate();
                        conn.close();
                        JOptionPane.showMessageDialog(null, "Registration successful.");

                        // Reset or empty the input fields
                        nameField.setText("");
                        passwordField.setText("");
                        emailField.setText("");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    // Create a connection to the MySQL database
    private Connection connect() {
        String url = "jdbc:mysql://localhost/user_registration";
        String user = "root";
        String password = "1234";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserRegistrationForm registrationForm = new UserRegistrationForm();
            registrationForm.setVisible(true);
        });
    }
}
