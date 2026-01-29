package domain;

import exceptions.EntidadException;

public class Alforja extends Objeto {

    public Alforja(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }
}
