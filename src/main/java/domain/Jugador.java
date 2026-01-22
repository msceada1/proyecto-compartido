package domain;

public class Jugador {

    private static final int MAX_CAPACIDAD_DE_INVENTARIO = 10;

    private Objeto[] inventario;

    public Jugador() {
        this.inventario = new Objeto[MAX_CAPACIDAD_DE_INVENTARIO];
    }

    public Objeto[] getInventario() {
        return inventario;
    }
}
