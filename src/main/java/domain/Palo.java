package domain;

import exceptions.EntidadException;

public class Palo extends Objeto implements Combinable {

    public Palo(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }

    @Override
    public Objeto combinar(Objeto objetoACombinar) throws EntidadException {
        if (!(objetoACombinar instanceof Combinable)){
            throw new EntidadException("No se pueden combinar los objetos");
        }
        return new Guadania("Guadania", "Resultado de haber combinando una hoz y un palo",true);
    }
}
