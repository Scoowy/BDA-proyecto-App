package utils;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import model.LocalDetail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase encargada de generar archivos de salida en distintos formatos.
 * <p>
 * <h3>Formatos implementados:</h3>
 * <ul>
 *     <li>CSV</li>
 *     <li>JSON</li>
 * </ul>
 *
 * @author Scoowy
 * @version 2020.08.01.1916
 */
public class FileOut {
    // Paths de salida
    private static final String PATH_JSON = "locals.json";
    private static final String PATH_CSV = "locals.csv";


    /**
     * Método que genera un archivo de salida en formato CSV.
     *
     * @param locals lista de locales.
     */
    public static void saveCSV(List<LocalDetail> locals) {
        // Nombres de columnas
        String[] header = new String[]{"id_estab", "nombre", "tipo_local", "calle_p", "calle_s", "referencia", "ciudad"};

        try {
            // Usamos la clase CSVWriter de OpenCSV
            CSVWriter writer = new CSVWriter(new FileWriter(PATH_CSV));
            // Escribimos la primera linea de nombres de columna
            writer.writeNext(header);

            // Recorremos la lista de locales
            for (LocalDetail local : locals) {
                // Por cada local escribimos una fila de elementos en el archivo de salida
                writer.writeNext(new String[]{
                        String.valueOf(local.getIdEstab()),
                        local.getNombre(),
                        local.getTipoLocal(),
                        local.getCalleP(),
                        local.getCalleS(),
                        local.getReferencia(),
                        local.getCiudad()
                });
            }
            // Cerramos el archivo para que los cambios se guarden
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que genera un archivo de salida en formato JSON.
     *
     * @param locals lista de locales.
     */
    public static void saveJSON(List<LocalDetail> locals) {
        // Escribimos un caracter [ para iniciar una lista en JSON
        StringBuilder localsJson = new StringBuilder("[");

        // Usamos la clase Gson de Gson - Google
        Gson gson = new Gson();

        // Recorremos la lista de locales
        for (int i = 0; i < locals.size(); i++) {
            // Instanciamos cada local
            LocalDetail local = locals.get(i);
            // Lo añadimos al string de salida JSON, mediante el método toJson de Gson
            // convierte cada objeto LocalDetail en una representación en formato JSON
            localsJson.append(gson.toJson(local));
            // Se separan por coma cada elemento, menos el ultimo
            if (i != 0) {
                localsJson.append(",");
            }
        }
        // Añadimos un caracter ] para cerrar la lista en JSON
        localsJson.append("]");

        // Creamos un fichero de escritura
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_JSON))) {
            // Escribimos el String que contien la lista de ciudades en JSON
            bw.write(localsJson.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que devuelve la ruta absoluta del archivo CSV.
     *
     * @return ruta absoluta del archivo CSV.
     */
    public static String getAbsolutePathCSVFile() {
        File file = new File(PATH_CSV);
        return file.getAbsolutePath();
    }

    /**
     * Método que devuelve la ruta absoluta del archivo JSON.
     *
     * @return ruta absoluta del archivo JSON.
     */
    public static String getAbsolutePathJSONFile() {
        File file = new File(PATH_JSON);
        return file.getAbsolutePath();
    }
}
