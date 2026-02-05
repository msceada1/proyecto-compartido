package domain;

public class Jugador {

    private static final int MAX_CAPACIDAD_DE_INVENTARIO = 10;
    private static final int INDICE_POSICION_INICIAL = 0;
    private int posicion;

    private Objeto[] inventario;

    public Jugador() {
        this.inventario = new Objeto[MAX_CAPACIDAD_DE_INVENTARIO];
        this.posicion = INDICE_POSICION_INICIAL;
    }

    public Objeto[] getInventario() {
        return inventario;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setInventario(Objeto[] inventario) {
        this.inventario = inventario;
    }

    public void addObjetoInventario(Objeto o) {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) {
                inventario[i] = o;
                return;
            }
        }
    }

    public void eliminarObjetoInventario(Objeto objetoAEliminar) {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null && inventario[i].getNombre().equalsIgnoreCase(String.valueOf(objetoAEliminar))) {
                inventario[i] = null;
                return;
            }
        }
    }
}
