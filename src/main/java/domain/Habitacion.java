package domain;

import exceptions.EntidadException;

public class Habitacion extends Entidad {

    private static final int MAX_CAPACIDAD_OBJETOS_POR_HABITACION = 5;

    private Objeto[] listaDeObjetos;

    public Habitacion(String nombre, String descripcion) throws EntidadException {
        super(nombre, descripcion);
        this.listaDeObjetos = new Objeto[MAX_CAPACIDAD_OBJETOS_POR_HABITACION];
    }
}
