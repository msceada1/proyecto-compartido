package domain;

import exceptions.EntidadException;

public class Guadania extends Objeto {
    public Guadania(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }
}
