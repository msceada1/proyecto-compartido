package domain;

import exceptions.CombinarException;
import exceptions.EntidadException;

public interface Combinable {

    Objeto combinar(Objeto objetoACombinar) throws EntidadException, CombinarException;
}
