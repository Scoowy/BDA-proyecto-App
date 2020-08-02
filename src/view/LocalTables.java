package view;

import dao.DataDB;
import model.LocalDetail;
import utils.FileOut;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class
 *
 * @author Scoowy
 * @version 2020.07.31.2140
 */
public class LocalTables extends JFrame {
    private JPanel pnlRoot;
    private JTable tblLocals;
    private JPanel pnlActions;
    private JComboBox cmbCities;
    private JButton btnDownload;
    private JButton btnSearch;
    private JRadioButton rdoCsv;
    private JRadioButton rdoJson;
    private JScrollPane scrollPane;

    private DataDB data = new DataDB();
    private List<LocalDetail> locals = new ArrayList<>();

    public LocalTables() {
        super("FindIt! - Locals");
        this.setContentPane(pnlRoot);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // ADD ActionsListeners
        this.btnSearch.addActionListener(e -> actionSearch(e));
        this.btnDownload.addActionListener(e -> actionDownload(e));

        // Fill ComboModel
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        loadCities(comboModel);
        this.cmbCities.setModel(comboModel);
    }

    private void actionSearch(ActionEvent e) {
        String city = (String) this.cmbCities.getSelectedItem();
        loadLocals(city);
    }

    private void actionDownload(ActionEvent e) {
        String message;
        if (!this.locals.isEmpty()) {
            message = "Archivo creado en la ruta:\n\t";
            if (this.rdoCsv.isSelected()) {
                FileOut.saveCSV(locals);
                message += FileOut.getAbsolutePathCSVFile();
            } else if (this.rdoJson.isSelected()) {
                FileOut.saveJSON(locals);
                message += FileOut.getAbsolutePathJSONFile();
            } else {
                message = "Seleccione un formato de archivo.";
            }
        } else {
            message = "No existen registros que guardar.";
        }
        JOptionPane.showMessageDialog(this, message);
    }

    private void loadCities(DefaultComboBoxModel model) {
        List<String> cities = data.getCities();
        for (String city : cities) {
            model.addElement(city);
        }
    }

    private void loadLocals(String city) {
        DefaultTableModel model = (DefaultTableModel) this.tblLocals.getModel();
        model.setRowCount(0);

        locals = data.getLocals(city);

        for (LocalDetail local : locals) {
            Object[] row = new Object[]{
                    local.getIdEstab(), local.getNombre(), local.getTipoLocal(), local.getCalleP(), local.getCalleS(), local.getReferencia(), local.getCiudad()
            };

            model.addRow(row);
        }
    }

    private void createUIComponents() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre", "Tipo", "C. Principal", "C. Secundaria", "Referencia", "Ciudad"}
        ) {
            Class[] types = new Class[]{
                    Integer.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        tblLocals = new JTable(model);
    }
}
