package presentation;

import business.implementation.OrderServiceImpl;
import business.service.OrderService;
import models.Order;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StaffOrderGUI extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public StaffOrderGUI() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(640,480);

        JPanel panel = new JPanel(null);
        panel.setSize(640, 480);
        add(panel);

        String[] columns = {"ID", "User ID", "Details", "Status"};
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

//        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent event) {
//                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
//                    CartService cartService = new CartServiceImpl();
//                    String name = table.getValueAt(table.getSelectedRow(), 1).toString();
//                    cartService.deleteItem(username, name);
//                    displayTable(username);
//                }
//            }
//        });

        panel.setVisible(true);
        setVisible(true);
    }

    private void displayTable() {
        String[] columns = {"ID", "User ID", "Details", "Status"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        OrderService orderService = new OrderServiceImpl();

        List<Order> orderList = orderService.getOrders();
        for (Order order : orderList) {
            Object[] row = {order.getId(), order.getUserID(), order.getDetails(), order.getStatus()};
            model.addRow(row);
        }
    }
}
