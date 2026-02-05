package domain;

import exceptions.EntidadException;

public class Habitacion extends Entidad {

    private Objeto[] listaDeObjetos;

    public Habitacion(String nombre, String descripcion, Objeto[] listaDeObjetos) throws EntidadException {
        super(nombre, descripcion);
        this.listaDeObjetos = listaDeObjetos;
    }

    public Objeto[] getListaDeObjetos() {
        return listaDeObjetos;
    }

    public void setListaDeObjetos(Objeto[] listaDeObjetos) {
        this.listaDeObjetos = listaDeObjetos;
    }
}
