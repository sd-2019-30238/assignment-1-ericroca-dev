package presentation;

import business.implementation.FeedbackServiceImpl;
import business.service.FeedbackService;

import javax.swing.*;
import java.awt.*;

public class FeedbackGUI extends JFrame {

    FeedbackGUI(Integer orderId, Integer userId) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(640,480);

        JPanel panel = new JPanel(null);
        panel.setSize(640, 480);
        add(panel);

        JLabel feedbackLabel = new JLabel();
        feedbackLabel.setBounds(240, 20, 80, 30);
        feedbackLabel.setText("Feedback:");
        panel.add(feedbackLabel);

        JTextArea feedbackTextArea = new JTextArea();
        feedbackTextArea.setBounds(240, 60, 200, 150);
        feedbackTextArea.setLineWrap(true);
        panel.add(feedbackTextArea);

        JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setBounds(220, 260, 240, 30);
        errorLabel.setForeground(Color.GREEN);
        panel.add(errorLabel);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(300, 220, 80, 30);
        submitButton.addActionListener((e) -> {
            String details = feedbackTextArea.getText();
            if (!details.equals("")) {
                FeedbackService feedbackService = new FeedbackServiceImpl();
                String error = feedbackService.submitFeedback(orderId, userId, details);
                errorLabel.setText(error);
            }
        });
        panel.add(submitButton);

        feedbackLabel.setVisible(true);
        feedbackTextArea.setVisible(true);
        errorLabel.setVisible(true);
        submitButton.setVisible(true);
        panel.setVisible(true);
        setVisible(true);
    }
}
