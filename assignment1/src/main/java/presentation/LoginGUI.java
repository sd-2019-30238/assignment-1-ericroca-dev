package presentation;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    public LoginGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(640,480);

        JPanel panel = new JPanel(null);
        panel.setSize(640, 480);
        add(panel);

        JLabel usernameLabel = new JLabel();
        usernameLabel.setBounds(200, 165, 80, 30);
        usernameLabel.setText("Username:");
        panel.add(usernameLabel);

        final JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(280, 165, 160, 30);
        panel.add(usernameTextField);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setBounds(200, 205, 80, 30);
        passwordLabel.setText("Password:");
        panel.add(passwordLabel);

        final JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(280, 205, 160, 30);
        panel.add(passwordTextField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 245, 70, 30);
        loginButton.addActionListener((e) -> {
//            String username = usernameTextField.getText();
//            String password = passwordTextField.getText();
        });
        panel.add(loginButton);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(280, 245, 150, 30);
        createAccountButton.addActionListener((e) -> {

        });
        panel.add(createAccountButton);

        JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setBounds(200, 285, 240, 30);
        errorLabel.setText("Error");
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel);

        usernameLabel.setVisible(true);
        usernameTextField.setVisible(true);
        passwordLabel.setVisible(true);
        passwordTextField.setVisible(true);
        loginButton.setVisible(true);
        createAccountButton.setVisible(true);
        errorLabel.setVisible(true);
        panel.setVisible(true);
        setVisible(true);
    }
}
