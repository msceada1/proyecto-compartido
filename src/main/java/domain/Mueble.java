package domain;

import exceptions.EntidadException;

public class Mueble extends Objeto {

    Objeto[] listaDeObjetosContenidos;

    public Mueble(String nombre, String descripcion, Objeto[] listaDeObjetosContenidos, boolean esVisible) throws EntidadException {
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
