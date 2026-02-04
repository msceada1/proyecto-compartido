package domain;

import exceptions.EntidadException;

public class Estanteria extends Contenedor implements Movible {
    public Estanteria(String nombre, String descripcion, String codigo, Objeto[] listaDeObjetosContenidos, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, codigo, listaDeObjetosContenidos, esVisible);
    }
}
