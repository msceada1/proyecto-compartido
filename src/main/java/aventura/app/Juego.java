package aventura.app;

import domain.Habitacion;
import domain.Inventariable;
import domain.Jugador;
import domain.Objeto;
import exceptions.EntidadException;
import io.MiEntradaSalida;

import java.util.Arrays;

public class Juego {

    private static final int INDICE_PRIMERA_HABITACION = 0;

    public static void main(String[] args) {

        Juego juego = new Juego();

        try {
            juego.inicializarJuego();
        } catch (EntidadException e) {
            e.getMessage();
        }
    }

    /**
     * Metodo que mueve al jugador a la derecha (controlando que no esté en el limite)
     * actualizando el indice de su posicion
     *
     * @param jugador      el jugador del juego
     * @param habitaciones las habitaciones que hay en la aplicacion
     */
    private void irALaDerecha(Jugador jugador, Habitacion[] habitaciones) {
        if (jugador.getPosicion() < habitaciones.length - 1) {
            jugador.setPosicion(jugador.getPosicion() + 1);
        } else {
            System.out.println("No hay nada más allá del " + habitaciones[jugador.getPosicion()].getNombre());
        }
    }

    /**
     * Metodo que mueve al jugador a la izquierda (controlando que no esté en el límite) actualizando el indice
     * de su posicion
     *
     * @param jugador      el jugador del juego
     * @param habitaciones las habitaciones que hay en la aplicacion
     */
    private void irALaIzquierda(Jugador jugador, Habitacion[] habitaciones) {
        if (jugador.getPosicion() > INDICE_PRIMERA_HABITACION) {
            jugador.setPosicion(jugador.getPosicion() - 1);
        } else {
            System.out.println("A la izquierda del " + habitaciones[jugador.getPosicion()].getNombre() + " no hay nada");
        }
    }

    /**
     * Metodo que accede al inventario del jugador y lo imprime por consola.
     *
     * @param jugador el jugador del juego
     */
    private void verInventario(Jugador jugador) {
        System.out.println("Inventario: " + Arrays.toString(jugador.getInventario()));
    }


    /**
     * Metodo que asigna los comandos que el usuario debe utilizar durante la ejecucion del programa
     *
     * @return los comandos que se usaran en el programa
     */
    private static String[] comandos() {

        String[] comandos = {"ayuda", "mirar", "ir a la izquierda", "ir a la derecha", "inventario", "coger objeto", "salir"};

        return comandos;
    }

    /**
     * Metodo que comprueba que el comando introducido sea valido
     *
     * @param comando  El comando que introduce el usuario
     * @param comandos La lista de comandos que se usan en el juego
     * @return true si el comando es valido, false en caso contrario
     */
    private static boolean comandoValido(String comando, String[] comandos) {

        for (int i = 0; i < comandos().length; i++) {
            if (comando.equalsIgnoreCase(comandos[i])) {
                return true;
            }
        }

        return false;
    }

    private void cogerObjeto(String nombreDelObjetoACoger, Habitacion habitacion, Jugador jugador) {
        if (elObjetoEstaEnLaHabitacion(nombreDelObjetoACoger, habitacion) == null) {
            System.out.println("El objeto " + nombreDelObjetoACoger + " no se encuentra en el " + habitacion.getNombre());
            return;
        }

        Objeto objetoParaInventario = elObjetoEstaEnLaHabitacion(nombreDelObjetoACoger, habitacion);

        if (!esUnObjetoInventariable(objetoParaInventario)) {
            System.out.println("No puedes equipar el objeto " + objetoParaInventario.getNombre());
            return;
        }

        if (elInventarioEstaLleno(jugador)) {
            System.out.println("El inventario está lleno, no puedes equipar nada más");
            return;
        }

        Objeto[] inventarioActualizado = jugador.getInventario();

        for (int i = 0; i < inventarioActualizado.length; i++) {
            if (inventarioActualizado[i] == null) {
                inventarioActualizado[i] = objetoParaInventario;
                jugador.setInventario(inventarioActualizado);
                eliminarObjetoDeLaHabitacion(objetoParaInventario, habitacion);
                return;
            }
        }
    }

    private Objeto elObjetoEstaEnLaHabitacion(String nombreDelObjetoACoger, Habitacion habitacion) {
        for (int i = 0; i < habitacion.getListaDeObjetos().length; i++) {
            if (habitacion.getListaDeObjetos()[i] != null) {
                if (habitacion.getListaDeObjetos()[i].getNombre().equalsIgnoreCase(nombreDelObjetoACoger)) {
                    return habitacion.getListaDeObjetos()[i];
                }
            }
        }
        return null;
    }

    private boolean esUnObjetoInventariable(Objeto objetoParaElInventario) {
        return objetoParaElInventario instanceof Inventariable;
    }

    private boolean elInventarioEstaLleno(Jugador jugador) {
        for (int i = 0; i < jugador.getInventario().length; i++) {
            if (jugador.getInventario()[i] == null) {
                return false;
            }
        }
        return true;
    }

    private void eliminarObjetoDeLaHabitacion(Objeto objeto, Habitacion habitacion) {
        Objeto[] objetosHabitacion = habitacion.getListaDeObjetos();

        for (int i = 0; i < objetosHabitacion.length; i++) {
            if (objetosHabitacion[i] == objeto) {
                objetosHabitacion[i] = null;
                habitacion.setListaDeObjetos(objetosHabitacion);
                return;
            }
        }
    }

    //a partir de aquí empieza el código de la fase 2
    private void inicializarJuego() throws EntidadException {

        //Listas de objetos que tiene cada habitacion
        Objeto[] listaDeObjetosDelAlmacen = {null, null, null, null, null};
        Objeto[] listaDeObjetosDelPasilloPrincipal = {null, null, null, null, null};
        Objeto[] listaDeObjetosDelEstablo = {null, null, null, null, null};

        //habitaciones
        Habitacion almacen = new Habitacion("Almacén", "Esto es el almacen, una pequeña habitación pero con varios misterios alrededor", listaDeObjetosDelAlmacen);
        Habitacion pasilloPrincipal = new Habitacion("Pasillo principal", "Esto es el pasillo principal, un pasillo largo y frío, tiene unas estanterías bastante llamativas", listaDeObjetosDelPasilloPrincipal);
        Habitacion establo = new Habitacion("Establo", "Esto es el establo donde se encuentran el caballo de Lucky Lucke con una preciosa montura cuyas alforjas podrían tener algo", listaDeObjetosDelEstablo);
        Habitacion habitaciones[] = {almacen, pasilloPrincipal, establo};

        //instancia del jugador
        Jugador jugador = new Jugador();

        boolean probando = true;
        while (probando) {
            String respuesta = MiEntradaSalida.leerCadena("Que accion deseas realizar?");

            if (respuesta.equalsIgnoreCase("Ir derecha")) {
                irALaDerecha(jugador, habitaciones);
            } else if (respuesta.equalsIgnoreCase("Ir izquierda")) {
                irALaIzquierda(jugador, habitaciones);
            } else if (respuesta.equalsIgnoreCase("Ver inventario")) {
                verInventario(jugador);
            }
        }

    }
}