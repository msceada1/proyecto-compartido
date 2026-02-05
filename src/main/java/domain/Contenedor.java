package domain;

import exceptions.EntidadException;

public class Contenedor extends Objeto implements Abrible {

    private String codigo;
    private Objeto[] listaDeObjetosContenidos;
    private boolean estaAbierto;

    public Contenedor(String nombre, String descripcion, String codigo, Objeto[] listaDeObjetosContenidos, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
        this.codigo = codigo;
        this.listaDeObjetosContenidos = listaDeObjetosContenidos;
        this.estaAbierto = false;
    }

    public boolean estaAbierto() {
        return estaAbierto;
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

    public void setEstaAbierto(boolean estaAbierto) {
        this.estaAbierto = estaAbierto;
    }

    @Override
    public RespuestaAccion abrir(Llave llave) {
        if (estaAbierto) {
            return new RespuestaAccion(false, "El " + getNombre() + " ya estaba abierto");
        }

        if (llave.getCodigoDeSeguridad().equalsIgnoreCase(this.codigo)) {
            setEstaAbierto(true);
            return new RespuestaAccion(true, "El " + getNombre() + " ha sido abierto");
        }

        return new RespuestaAccion(false, "No puedes abrir el " + getNombre() + " algo falla");
    }

    public void eliminarObjetos() {
        for (int i = 0; i < listaDeObjetosContenidos.length; i++) {
            if (listaDeObjetosContenidos[i] != null) {
                listaDeObjetosContenidos[i] = null;
            }
        }
    }
}
