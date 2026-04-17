package aventura.domain;

import aventura.exceptions.AventuraException;
import aventura.exceptions.InventarioLlenoException;

import java.util.ArrayList;

public class Jugador {
    private static final int MAX_INVENTARIO = 10;

    private String nombre;
    private ArrayList<Objeto> inventario = new ArrayList<>();
    private String habitacionActual;

    /**
     * Constructor de la clase Jugador.
     *
     * @param nombre Nombre del jugador.
     */
    public Jugador(String nombre, String habitacionActual) {
        this.nombre = nombre;
        this.habitacionActual = habitacionActual;
    }

    /** Getters y Setters */
    public String getNombre() {
        return nombre;
    }

    public String getHabitacionActual() {
        return habitacionActual;
    }

    public void setHabitacionActual(String habitacionActual) {
        if (habitacionActual == null){
            habitacionActual = "0";
        }

        this.habitacionActual = habitacionActual;
    }

    public ArrayList<Objeto> getInventario() {
        return inventario;
    }

    /**
     * Método para que el jugador coja un objeto.
     *
     * @param objeto Objeto a coger.
     * @throws AventuraException Si el objeto no es inventariable o el inventario está lleno.
     */
    public void coger(Objeto objeto) throws AventuraException {
        /*
        Para poder coger un objeto, deben pasar dos cosas:
        1. Que el objeto sea Inventariable
        2. Que haya espacio en el inventario
        */

        if (!(objeto instanceof Inventariable)) {
            throw new AventuraException("El objeto %s no se puede coger.".formatted(objeto.getNombre()));
        }

        if (inventario.size() >= MAX_INVENTARIO) {
            throw new AventuraException("Tienes el inventario lleno");
        }
        inventario.add(objeto);
    }

    /**
     * Método para eliminar un objeto del inventario.
     * @param objeto Objeto a eliminar.
     * @return true si se eliminó el objeto, false si no se encontró.
     */
    public boolean eliminarDeInventario(Objeto objeto) {
       return inventario.remove(objeto);
    }

    /**
     * Busca un objeto en el inventario por su nombre.
     * @param nombre Nombre del objeto a buscar.
     * @return El objeto si se encuentra, null en caso contrario.
     */
    public Objeto buscarEnInventario(String nombre) {
        for (Objeto objeto : inventario) {
            if (objeto != null && objeto.getNombre().equalsIgnoreCase(nombre)) {
                return objeto;
            }
        }
        return null; // No lo tienes encima
    }
}
