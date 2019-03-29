package presentation;

import business.implementation.CartServiceImpl;
import business.implementation.DealServiceImpl;
import business.implementation.OrderServiceImpl;
import business.service.CartService;
import business.service.DealService;
import business.service.OrderService;
import models.Deal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
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

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(200, 40, 120, 30);
        checkoutButton.addActionListener((e) -> {
            List<String> names = new ArrayList<>();
            List<String> prices = new ArrayList<>();
            for (int row = 0; row < model.getRowCount(); row++){
                for (int column = 0; column < model.getColumnCount() - 1; column++){
                    if (column == 0) {
                        prices.add(model.getValueAt(row, column).toString());
                    } else if (column == 1) {
                        names.add(model.getValueAt(row, column).toString());
                    }
                }
            }
            OrderService orderService = new OrderServiceImpl();
            orderService.checkout(username, names, prices);
            displayTable(username);
        });
        panel.add(checkoutButton);

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

        checkoutButton.setVisible(true);
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
