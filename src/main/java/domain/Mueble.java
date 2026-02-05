package domain;

import exceptions.EntidadException;

public class Mueble extends Objeto {

    public Mueble(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }
}
