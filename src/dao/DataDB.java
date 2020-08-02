package dao;

import model.LocalDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class
 *
 * @author Scoowy
 * @version 2020.08.01.1636
 */
public class DataDB extends DBConn {

    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        cities.add("ALL");

        String query = "SELECT DISTINCT ciudad FROM direccion";

        try {
            this.connect();
            PreparedStatement st = this.conn.prepareStatement(query);

            ResultSet res = st.executeQuery();

            while (res.next()) {
                String city = res.getString("ciudad");
                cities.add(city);
            }
            res.close();
            st.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            this.close();
        }

        return cities;
    }

    /**
     * Método encargado de obtener la información de los locales, desde la base de datos.
     *
     * @param city ciudad por la que se filtra la búsqueda.
     * @return una lista de locales.
     */
    public List<LocalDetail> getLocals(String city) {
        // Lista vacía
        List<LocalDetail> locals = new ArrayList<>();
        // Consulta SQL
        String query = "SELECT es.id_estab, es.nombre, es.tipo_local, di.calle_p, di.calle_s, di.referencia, di.ciudad\n" +
                "FROM establecimiento es,\n" +
                "     direccion di\n" +
                "WHERE di.id_direccion = es.direccion";

        // Si la opción de ciudad es diferente de ALL, se añade una condición mas al WHERE
        if (!city.equals("ALL")) {
            query += "\n    AND di.ciudad = ?";
        }

        try {
            this.connect(); // Conectamos la DB
            // Preparamos la consulta
            PreparedStatement st = this.conn.prepareStatement(query);
            if (!city.equals("ALL")) {
                st.setString(1, city);
            }
            ResultSet res = st.executeQuery(); // Ejecutamos la consulta

            // Recorremos los resultados de la consulta
            while (res.next()) {
                // Creamos y añadimos los locales
                LocalDetail local = new LocalDetail(
                        res.getInt("id_estab"),
                        res.getString("nombre"),
                        res.getString("tipo_local"),
                        res.getString("calle_p"),
                        res.getString("calle_s"),
                        res.getString("referencia"),
                        res.getString("ciudad")
                );
                locals.add(local);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return locals; // Retornamos la lista de locales
    }
}
