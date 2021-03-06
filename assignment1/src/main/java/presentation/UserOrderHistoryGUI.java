package presentation;

import business.interfaces.read.LoginRead;
import business.interfaces.read.OrderRead;
import business.services.read.LoginReadService;
import business.services.read.OrderReadService;
import models.Order;
import models.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class UserOrderHistoryGUI extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private List<Order> orderList;

    public UserOrderHistoryGUI(String username) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(640,480);

        JPanel panel = new JPanel(null);
        panel.setSize(640, 480);
        add(panel);

        String[] columns = {"Details"};
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

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    Order order = orderList.get(table.getSelectedRow());

                    LoginRead loginReadService = new LoginReadService();
                    User user = loginReadService.getUserByUsername(username);

                    new FeedbackGUI(order.getId(), user.getId());
                }
            }
        });

        panel.setVisible(true);
        setVisible(true);
    }

    private void displayTable(String username) {
        String[] columns = {"Details"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        OrderRead orderReadService = new OrderReadService();

        orderList = orderReadService.getUserDeliveredOrders(username);
        for (Order order : orderList) {
            Object[] row = {order.getDetails(), order.getStatus()};
            model.addRow(row);
        }
    }
}
