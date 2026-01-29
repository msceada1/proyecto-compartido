package domain;

import exceptions.EntidadException;

public class Palo extends Objeto {

    public Palo(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }
}
