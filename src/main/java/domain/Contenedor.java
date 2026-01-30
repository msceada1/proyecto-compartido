package domain;

import exceptions.EntidadException;

public class Contenedor extends Objeto {

    private String codigo;

    private Objeto[] listaDeObjetosContenidos;

    public Contenedor(String nombre, String descripcion, String codigo, Objeto[] listaDeObjetosContenidos, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
        this.codigo = codigo;
        this.listaDeObjetosContenidos = listaDeObjetosContenidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Objeto[] getListaDeObjetosContenidos() {
        return listaDeObjetosContenidos;
    }

    public void setListaDeObjetosContenidos(Objeto[] listaDeObjetosContenidos) {
        this.listaDeObjetosContenidos = listaDeObjetosContenidos;
    }
}
