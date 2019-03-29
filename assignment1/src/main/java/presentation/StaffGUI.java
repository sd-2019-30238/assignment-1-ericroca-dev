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

        JLabel idLabel = new JLabel();
        idLabel.setBounds(40, 20, 40, 30);
        idLabel.setText("ID:");
        panel.add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(80, 20, 80, 30);
        panel.add(idTextField);

        JLabel priceLabel = new JLabel();
        priceLabel.setBounds(160, 20, 40, 30);
        priceLabel.setText("Price:");
        panel.add(priceLabel);

        JTextField priceTextField = new JTextField();
        priceTextField.setBounds(200, 20, 80, 30);
        panel.add(priceTextField);

        JLabel nameLabel = new JLabel();
        nameLabel.setBounds(280, 20, 40, 30);
        nameLabel.setText("Name:");
        panel.add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(320, 20, 80, 30);
        panel.add(nameTextField);

        JLabel typeLabel = new JLabel();
        typeLabel.setBounds(400, 20, 40, 30);
        typeLabel.setText("Type:");
        panel.add(typeLabel);

        JTextField typeTextField = new JTextField();
        typeTextField.setBounds(440, 20, 80, 30);
        panel.add(typeTextField);

        JLabel quantityLabel = new JLabel();
        quantityLabel.setBounds(520, 20, 40, 30);
        quantityLabel.setText("Quantity:");
        panel.add(quantityLabel);

        JTextField quantityTextField = new JTextField();
        quantityTextField.setBounds(560, 20, 80, 30);
        panel.add(quantityTextField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(40, 60, 80, 30);
        addButton.addActionListener((e) -> {
            String priceText = priceTextField.getText();
            String name = nameTextField.getText();
            String type = typeTextField.getText();
            String quantityText = quantityTextField.getText();

            if (!priceText.equals("") && !name.equals("") && !type.equals("") &&
                    !quantityText.equals("")) {
                Double price = Double.valueOf(priceText);
                Integer quantity = Integer.valueOf(quantityText);
                DealService dealService = new DealServiceImpl();
                dealService.addDeal(price, name, type, quantity);
                displayTable();
            }
        });
        panel.add(addButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(120, 60, 80, 30);
        editButton.addActionListener((e) -> {
            String idText = idTextField.getText();
            String priceText = priceTextField.getText();
            String name = nameTextField.getText();
            String type = typeTextField.getText();
            String quantityText = quantityTextField.getText();

            if (!idText.equals("") && !priceText.equals("") && !name.equals("") && !type.equals("") &&
                    !quantityText.equals("")) {
                Integer id = Integer.valueOf(idText);
                Double price = Double.valueOf(priceText);
                Integer quantity = Integer.valueOf(quantityText);
                DealService dealService = new DealServiceImpl();
                dealService.editDeal(id, price, name, type, quantity);
                displayTable();
            }
        });
        panel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(200, 60, 80, 30);
        deleteButton.addActionListener((e) -> {
            String idText = idTextField.getText();

            if (!idText.equals("")) {
                Integer id = Integer.valueOf(idText);
                DealService dealService = new DealServiceImpl();
                dealService.deleteDeal(id);
                displayTable();
            }
        });
        panel.add(deleteButton);

        JButton viewOrdersButton = new JButton("View orders");
        viewOrdersButton.setBounds(280, 60, 80, 30);
        viewOrdersButton.addActionListener((e) -> {

        });

        String[] columns = {"ID", "Price", "Name", "Type", "Quantity"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 120, 560, 300);
        panel.add(pane);

        displayTable();

        idLabel.setVisible(true);
        idTextField.setVisible(true);
        priceLabel.setVisible(true);
        priceTextField.setVisible(true);
        nameLabel.setVisible(true);
        nameTextField.setVisible(true);
        typeLabel.setVisible(true);
        typeTextField.setVisible(true);
        quantityLabel.setVisible(true);
        quantityTextField.setVisible(true);
        addButton.setVisible(true);
        editButton.setVisible(true);
        deleteButton.setVisible(true);
        panel.setVisible(true);
        setVisible(true);
    }

    private void displayTable() {
        String[] columns = {"ID", "Price", "Name", "Type", "Quantity"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        DealService dealService = new DealServiceImpl();

        List<Deal> dealList = dealService.getDeals();
        for (Deal deal : dealList) {
            Object[] row = {deal.getId(), deal.getPrice(), deal.getName(), deal.getType(), deal.getQuantity()};
            model.addRow(row);
        }
    }
}
