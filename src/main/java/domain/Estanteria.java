package domain;

import exceptions.EntidadException;

public class Estanteria extends Objeto{

    public Estanteria(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }
}
