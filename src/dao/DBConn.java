package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class
 *
 * @author Scoowy
 * @version 2020.08.01.1525
 */
public class DBConn {
    protected Connection conn;

    // JDBC driver nombre y base de datos URL
    private final String JDBC_DRIVER = "jdbc:sqlite:";
    private final String DB_PATH = "FindItDB.sqlite";

    public void connect() {
        try {
            conn = DriverManager.getConnection(JDBC_DRIVER + DB_PATH);
            System.out.println("Conexión correcta.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void close() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    System.out.println("Conexión cerrada.");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
