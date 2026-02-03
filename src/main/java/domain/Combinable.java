package domain;

import exceptions.EntidadException;

public interface Combinable {

    Objeto combinar(Objeto objetoACombinar) throws EntidadException;
}
