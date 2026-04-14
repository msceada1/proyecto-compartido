package aventura.domain;

import aventura.exceptions.AventuraException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Habitacion {
    private static final int MAX_OBJETOS = 5;
    private String nombre;
    private String descripcion;
    private List<Objeto> objetos; // Lista de objetos reales, no Strings
    private Map<String, String> salidas;

    /**
     * Constructor de la clase habitacion
     *
     * @param nombre      el nombre de la habitacion
     * @param descripcion la descripcion de la habitacion
     */
    public Habitacion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.objetos = new ArrayList<>(MAX_OBJETOS);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setObjetos(List<Objeto> objetos) {
        this.objetos = objetos;
    }

    public Map<String, String> getSalidas() {
        return salidas;
    }

    public void setSalidas(Map<String, String> salidas) {
        this.salidas = salidas;
    }

    /**
     * Agrega un objeto a la habitación.
     *
     * @param obj Objeto a agregar.
     * @throws AventuraException Si no se puede agregar el objeto.
     */
    public void agregarObjeto(Objeto obj) throws AventuraException {
        // Lógica para añadir en el primer hueco null
        boolean added = false;
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] == null) {
                objetos[i] = obj;
                return;
            }
        }
        if (!added) {
            throw new AventuraException("No se puede agregar un objeto");
        }
    }

    /**
     * Elimina un objeto de la habitación.
     *
     * @param obj Objeto a eliminar.
     * @return true si se eliminó el objeto, false si no se encontró.
     */
    public boolean eliminarObjeto(Objeto obj) {
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null && objetos[i].equals(obj)) {
                objetos[i] = null;
                return true;
            }
        }

        return false;
    }

    /**
     * Muestra la descripción de la habitación y los objetos presentes en ella.
     *
     * @return Descripción de la habitación y lista de objetos.
     */
    public String mirar() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.descripcion).append("\n");
        for (Objeto obj : objetos) {
            if (obj != null) {
                sb.append(" - ").append(obj.getNombre()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Obtiene los objetos presentes en la habitación.
     *
     * @return Array de objetos en la habitación.
     */
    public Objeto[] getObjetos() {
        return objetos;
    }

    /**
     * Busca un objeto por su nombre en la habitación.
     *
     * @param nombre Nombre del objeto a buscar.
     * @return El objeto si se encuentra, null en caso contrario.
     */
    public Objeto buscar(String nombre) {
        for (Objeto obj : objetos) {
            if (obj != null && obj.getNombre().equalsIgnoreCase(nombre)) {
                return obj;
            }
        }
        return null;
    }
}
