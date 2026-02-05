package domain;

import exceptions.EntidadException;

public class Alforja extends Objeto {

    Objeto[] listaDeObjetosContenidos;

    public Alforja(String nombre, String descripcion, Objeto[] listaDeObjetosContenidos, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
        this.listaDeObjetosContenidos = listaDeObjetosContenidos;
    }

    public Objeto[] getListaDeObjetosContenidos() {
        return listaDeObjetosContenidos;
    }

    public void setListaDeObjetosContenidos(Objeto[] listaDeObjetosContenidos) {
        this.listaDeObjetosContenidos = listaDeObjetosContenidos;
    }
}
