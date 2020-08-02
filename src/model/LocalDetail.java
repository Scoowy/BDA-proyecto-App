package model;

/**
 * Class
 *
 * @author Scoowy
 * @version 2020.08.01.1647
 */
public class LocalDetail {
    private int idEstab;
    private String nombre;
    private String tipoLocal;
    private String calleP;
    private String calleS;
    private String referencia;
    private String ciudad;

    public LocalDetail(int idEstab, String nombre, String tipoLocal, String calleP, String calleS, String referencia, String ciudad) {
        this.idEstab = idEstab;
        this.nombre = nombre;
        this.tipoLocal = tipoLocal;
        this.calleP = calleP;
        this.calleS = calleS;
        this.referencia = referencia;
        this.ciudad = ciudad;
    }

    public int getIdEstab() {
        return idEstab;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoLocal() {
        return tipoLocal;
    }

    public String getCalleP() {
        return calleP;
    }

    public String getCalleS() {
        return calleS;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getCiudad() {
        return ciudad;
    }
}
