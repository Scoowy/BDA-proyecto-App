package finditlocals;

import com.formdev.flatlaf.FlatLightLaf;
import view.LocalTables;

import javax.swing.*;

/**
 * Class
 *
 * @author Scoowy
 * @version 2020.07.31.2137
 */
public class FindItLocals {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Fallo al cargar el tema.");
        }

        LocalTables view = new LocalTables();
    }
}
