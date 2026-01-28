package domain;

import exceptions.EntidadException;

public class Latigo extends Objeto implements Inventariable {

    public Latigo(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }
}
