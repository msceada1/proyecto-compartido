package domain;

import exceptions.EntidadException;

public abstract class Objeto extends Entidad {

    private boolean esVisible;

    public Objeto(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion);
        this.esVisible = esVisible;
    }

    public boolean isEsVisible() {
        return esVisible;
    }

    public void setEsVisible(boolean esVisible) {
        this.esVisible = esVisible;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                super.toString() +
                '}';
    }
}
