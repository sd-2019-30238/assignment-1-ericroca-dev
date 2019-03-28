package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserGUI extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public UserGUI() {
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

        panel.setVisible(true);
        setVisible(true);
    }

    private void displayTable() {
        String[] columns = {"Price", "Name", "Type"};
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

//        List<Employee> employeeList = adminService.getEmployees();
//        for (Employee employee : employeeList) {
//            Object[] row = {employee.getId(), employee.getFirstName(), employee.getLastName(),
//                    employee.getUsername(), employee.getPassword()};
//            model.addRow(row);
//        }
    }
}
