package domain;

import exceptions.EntidadException;

public class Nota extends Objeto implements Leible {

    private String texto;

    public Nota(String nombre, String descripcion, boolean esVisible, String texto) throws EntidadException {
        super(nombre, descripcion, esVisible);
        this.texto = texto;
    }

    @Override
    public String leer() {
        return texto;
    }
}
