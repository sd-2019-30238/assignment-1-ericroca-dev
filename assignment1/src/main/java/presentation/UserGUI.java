package presentation;

import business.interfaces.write.CartWrite;
import business.interfaces.read.DealRead;
import business.services.write.CartWriteService;
import business.services.read.DealReadService;
import models.Deal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserGUI extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public UserGUI(String username) {
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
        searchButton.setBounds(40, 60, 120, 30);
        searchButton.addActionListener((e) -> {
            String priceText = priceTextField.getText();
            String name = nameTextField.getText();
            String type = typeTextField.getText();

            if (!priceText.equals("") && !name.equals("") && !type.equals("")) {
                Double price = Double.valueOf(priceText);
                DealRead dealReadService = new DealReadService();
                List<Deal> dealList = dealReadService.getFilteredDeals(price, name, type);
                displayTable(dealList);
            } else if (!priceText.equals("") && !name.equals("")) {
                Double price = Double.valueOf(priceText);
                DealRead dealReadService = new DealReadService();
                List<Deal> dealList = dealReadService.getFilteredDealsByPriceAndName(price, name);
                displayTable(dealList);
            } else if (!priceText.equals("") && !type.equals("")) {
                Double price = Double.valueOf(priceText);
                DealRead dealReadService = new DealReadService();
                List<Deal> dealList = dealReadService.getFilteredDealsByPriceAndType(price, type);
                displayTable(dealList);
            } else if (!name.equals("") && !type.equals("")) {
                DealRead dealReadService = new DealReadService();
                List<Deal> dealList = dealReadService.getFilteredDealsByNameAndType(name, type);
                displayTable(dealList);
            } else if (!priceText.equals("")) {
                Double price = Double.valueOf(priceText);
                DealRead dealReadService = new DealReadService();
                List<Deal> dealList = dealReadService.getFilteredDealsByPrice(price);
                displayTable(dealList);
            } else if (!name.equals("")) {
                DealRead dealReadService = new DealReadService();
                List<Deal> dealList = dealReadService.getFilteredDealsByName(name);
                displayTable(dealList);
            } else if (!type.equals("")) {
                DealRead dealReadService = new DealReadService();
                List<Deal> dealList = dealReadService.getFilteredDealsByType(type);
                displayTable(dealList);
            } else {
                displayTable();
            }
        });
        panel.add(searchButton);

        JButton viewCartButton = new JButton("View cart");
        viewCartButton.setBounds(160, 60, 120, 30);
        viewCartButton.addActionListener((e) -> {
            new CartGUI(username);
        });
        panel.add(viewCartButton);

        JButton viewOrdersButton = new JButton("View orders");
        viewOrdersButton.setBounds(280, 60, 120, 30);
        viewOrdersButton.addActionListener((e) -> {
            new UserOrderGUI(username);
        });
        panel.add(viewOrdersButton);

        JButton viewOrderHistoryButton = new JButton("Order history");
        viewOrderHistoryButton.setBounds(400, 60, 120, 30);
        viewOrderHistoryButton.addActionListener((e) -> {
            new UserOrderHistoryGUI(username);
        });
        panel.add(viewOrderHistoryButton);

        String[] columns = {"Price", "Name", "Type"};
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

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    CartWrite cartWriteService = new CartWriteService();
                    String name = table.getValueAt(table.getSelectedRow(), 1).toString();
                    cartWriteService.addToCart(username, name);
                }
            }
        });

        priceLabel.setVisible(true);
        priceTextField.setVisible(true);
        nameLabel.setVisible(true);
        nameTextField.setVisible(true);
        typeLabel.setVisible(true);
        typeTextField.setVisible(true);
        searchButton.setVisible(true);
        viewCartButton.setVisible(true);
        viewOrdersButton.setVisible(true);
        viewOrderHistoryButton.setVisible(true);
        panel.setVisible(true);
        setVisible(true);
    }

    private void displayTable() {
        String[] columns = {"Price", "Name", "Type"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        DealRead dealReadService = new DealReadService();

        List<Deal> dealList = dealReadService.getDeals();
        for (Deal deal : dealList) {
            Object[] row = {deal.getPrice(), deal.getName(), deal.getType()};
            model.addRow(row);
        }
    }

    private void displayTable(List<Deal> dealList) {
        String[] columns = {"Price", "Name", "Type"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        for (Deal deal : dealList) {
            Object[] row = {deal.getPrice(), deal.getName(), deal.getType()};
            model.addRow(row);
        }
    }
}
