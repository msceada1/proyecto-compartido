package domain;

import exceptions.EntidadException;

public class Revolver extends Objeto implements Inventariable {

    private int capacidadBalas;

    public Revolver(String nombre, String descripcion, int capacidadBalas, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
        this.capacidadBalas = capacidadBalas;
    }

    public int getCapacidadBalas() {
        return capacidadBalas;
    }
}
