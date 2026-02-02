package domain;

public record RespuestaAccion(boolean esExito, String mensaje) {

    public RespuestaAccion(boolean esExito, String mensaje) {
        this.esExito = esExito;
        this.mensaje = mensaje;
    }

    @Override
    public boolean esExito() {
        return esExito;
    }

    @Override
    public String mensaje() {
        return mensaje;
    }
}
