package domain;

import exceptions.EntidadException;

public abstract class Entidad {

    private String nombre;
    private String descripcion;

    public Entidad(String nombre, String descripcion) throws EntidadException {
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws EntidadException {
        if (nombre == null || nombre.isBlank()) {
            throw new EntidadException("ERROR: La entidad debe tener nombre");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) throws EntidadException {
        if (descripcion == null || descripcion.isBlank()) {
            throw new EntidadException("ERROR: La entidad debe tener decripción");
        }
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
