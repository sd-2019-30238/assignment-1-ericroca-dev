package presentation;

import business.implementation.DealServiceImpl;
import business.service.DealService;
import models.Deal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StaffGUI extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public StaffGUI() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(640,480);

        JPanel panel = new JPanel(null);
        panel.setSize(640, 480);
        add(panel);

        JLabel priceLabel = new JLabel();
        priceLabel.setBounds(40, 20, 40, 30);
        priceLabel.setText("Price:");
        panel.add(priceLabel);

        JTextField priceTextField = new JTextField();
        priceTextField.setBounds(80, 20, 80, 30);
        panel.add(priceTextField);

        JLabel nameLabel = new JLabel();
        nameLabel.setBounds(160, 20, 40, 30);
        nameLabel.setText("Name:");
        panel.add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(200, 20, 80, 30);
        panel.add(nameTextField);

        JLabel typeLabel = new JLabel();
        typeLabel.setBounds(280, 20, 40, 30);
        typeLabel.setText("Type:");
        panel.add(typeLabel);

        JTextField typeTextField = new JTextField();
        typeTextField.setBounds(320, 20, 80, 30);
        panel.add(typeTextField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(400, 20, 80, 30);
        searchButton.addActionListener((e) -> {

        });
        panel.add(searchButton);

        JButton viewCartButton = new JButton("View cart");
        viewCartButton.setBounds(480, 20, 127, 30);
        viewCartButton.addActionListener((e) -> {

        });
        panel.add(viewCartButton);

        String[] columns = {"Price", "Name", "Type"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 80, 560, 340);
        panel.add(pane);

        displayTable();

        priceLabel.setVisible(true);
        priceTextField.setVisible(true);
        nameLabel.setVisible(true);
        nameTextField.setVisible(true);
        typeLabel.setVisible(true);
        typeTextField.setVisible(true);
        panel.setVisible(true);
        setVisible(true);
    }

    private void displayTable() {
        String[] columns = {"Price", "Name", "Type"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        DealService dealService = new DealServiceImpl();

        List<Deal> dealList = dealService.getDeals();
        for (Deal deal : dealList) {
            Object[] row = {deal.getPrice(), deal.getName(), deal.getType()};
            model.addRow(row);
        }
    }
}
