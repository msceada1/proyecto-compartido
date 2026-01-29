package domain;

import exceptions.EntidadException;

public class Hoz extends Objeto {

    public Hoz(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }
}
