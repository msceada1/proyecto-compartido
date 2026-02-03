package domain;

import exceptions.EntidadException;

public class Hoz extends Objeto implements Combinable {

    public Hoz(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }

    @Override
    public Objeto combinar(Objeto objetoACombinar) throws EntidadException {
        if (objetoACombinar != null) {
            if (objetoACombinar instanceof Palo p) {
                Objeto aux = p.combinar(this);
                return aux;
            }
        }
        return null;
    }
}
