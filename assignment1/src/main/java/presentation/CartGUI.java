package presentation;

import business.implementation.CartServiceImpl;
import business.implementation.DealServiceImpl;
import business.service.CartService;
import business.service.DealService;
import models.Deal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CartGUI extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public CartGUI(String username) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(640,480);

        JPanel panel = new JPanel(null);
        panel.setSize(640, 480);
        add(panel);

        JButton viewOrdersButton = new JButton("View orders");
        viewOrdersButton.setBounds(260, 40, 120, 30);
        viewOrdersButton.addActionListener((e) -> {

        });
        panel.add(viewOrdersButton);

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

        displayTable(username);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    CartService cartService = new CartServiceImpl();
                    String name = table.getValueAt(table.getSelectedRow(), 1).toString();
                    cartService.deleteItem(username, name);
                    displayTable(username);
                }
            }
        });

        viewOrdersButton.setVisible(true);
        panel.setVisible(true);
        setVisible(true);
    }

    private void displayTable(String username) {
        String[] columns = {"Price", "Name", "Type"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        CartService cartService = new CartServiceImpl();

        List<Deal> dealList = cartService.getUserCart(username);
        for (Deal deal : dealList) {
            Object[] row = {deal.getPrice(), deal.getName(), deal.getType()};
            model.addRow(row);
        }
    }
}
