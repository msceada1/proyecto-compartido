package domain;

import exceptions.EntidadException;

public class Llave extends Objeto {

    private String codigoDeSeguridad;

    public Llave(String nombre, String descripcion, String codigoDeSeguridad, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
        this.codigoDeSeguridad = codigoDeSeguridad;
    }

    public String getCodigoDeSeguridad() {
        return codigoDeSeguridad;
    }
}
