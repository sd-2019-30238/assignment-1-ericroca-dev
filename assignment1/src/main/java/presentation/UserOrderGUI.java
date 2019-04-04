package presentation;

import business.implementation.OrderServiceImpl;
import business.service.OrderService;
import models.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class UserOrderGUI extends JFrame {


    private DefaultTableModel model;
    private JTable table;

    public UserOrderGUI(String username) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(640,480);

        JPanel panel = new JPanel(null);
        panel.setSize(640, 480);
        add(panel);

        String[] columns = {"Details", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            public String getToolTipText(MouseEvent event) {
                String tip = null;
                Point point = event.getPoint();
                int rowIndex = rowAtPoint(point);
                int colIndex = columnAtPoint(point);

                try {
                    tip = getValueAt(rowIndex, colIndex).toString();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

                return tip;
            }
        };

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 40, 560, 380);
        panel.add(pane);

        displayTable(username);

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

    private void displayTable(String username) {
        String[] columns = {"Details", "Status"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        OrderService orderService = new OrderServiceImpl();

        List<Order> orderList = orderService.getUserOrders(username);
        for (Order order : orderList) {
            Object[] row = {order.getDetails(), order.getStatus()};
            model.addRow(row);
        }
    }
}
