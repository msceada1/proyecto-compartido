package aventura.domain;

import java.util.HashMap;
import java.util.Map;

public class AventuraConfig {
    private String descripcion;
    private Map<String, Habitacion> sala;

    public AventuraConfig(String descripcion, Map<String, Habitacion> sala) {
        this.descripcion = descripcion;
        this.sala = sala;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Map<String, Habitacion> getSala() {
        return sala;
    }

}
