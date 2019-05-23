package presentation;

import business.interfaces.read.OrderRead;
import business.interfaces.write.OrderWrite;
import business.services.read.OrderReadService;
import business.services.write.OrderWriteService;
import models.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
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

        JLabel idLabel = new JLabel();
        idLabel.setBounds(40, 20, 60, 30);
        idLabel.setText("ID:");
        panel.add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(100, 20, 80, 30);
        panel.add(idTextField);

        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(180, 20, 60, 30);
        statusLabel.setText("Status:");
        panel.add(statusLabel);

        JTextField statusTextField = new JTextField();
        statusTextField.setBounds(240, 20, 80, 30);
        panel.add(statusTextField);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(320, 20, 80, 30);
        updateButton.addActionListener((e) -> {
            String idText = idTextField.getText();
            String status = statusTextField.getText();

            if (!idText.equals("") && !status.equals("")) {
                Integer id = Integer.valueOf(idText);
                OrderWrite orderWriteService = new OrderWriteService();
                orderWriteService.updateStatus(id, status);
                displayTable();
            }
        });
        panel.add(updateButton);

        String[] columns = {"ID", "User ID", "Details", "Status"};
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

        idLabel.setVisible(true);
        idTextField.setVisible(true);
        statusLabel.setVisible(true);
        statusTextField.setVisible(true);
        updateButton.setVisible(true);
        panel.setVisible(true);
        setVisible(true);
    }

    private void displayTable() {
        String[] columns = {"ID", "User ID", "Details", "Status"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        OrderRead orderReadService = new OrderReadService();

        List<Order> orderList = orderReadService.getOrders();
        for (Order order : orderList) {
            Object[] row = {order.getId(), order.getUserID(), order.getDetails(), order.getStatus()};
            model.addRow(row);
        }
    }
}
