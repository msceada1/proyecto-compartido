package domain;

import exceptions.EntidadException;

public class Contenedor extends Objeto {

    private String codigo;

    public Contenedor(String nombre, String descripcion, String codigo, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
