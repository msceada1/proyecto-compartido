package domain;

import exceptions.CombinarException;
import exceptions.EntidadException;

public class Palo extends Objeto implements Combinable {

    public Palo(String nombre, String descripcion, boolean esVisible) throws EntidadException {
        super(nombre, descripcion, esVisible);
    }

    @Override
    public Objeto combinar(Objeto objetoACombinar) throws CombinarException, EntidadException {
        if (!(objetoACombinar instanceof Combinable)){
            throw new CombinarException("No se pueden combinar los objetos");
        }
        return new Guadania("Guadania", "Resultado de haber combinando una hoz y un palo",true);
    }
}
