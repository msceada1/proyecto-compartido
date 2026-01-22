package aventura.app;

import domain.Habitacion;
import domain.Jugador;
import domain.Objeto;
import exceptions.EntidadException;
import io.MiEntradaSalida;

import java.util.Arrays;

public class Juego {

    private static final int INDICE_PRIMERA_HABITACION = 0;

    public static void main(String[] args) {

        Juego juego = new Juego();

    }

    /**
     * Metodo que mueve al jugador a la derecha (controlando que no esté en el limite)
     * actualizando el indice de su posicion
     *
     * @param jugador el jugador del juego
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
     * Metodo que mueve la posicion a la izquierda, asegurando que si esta en el limite izquierdo no pueda
     * realizar el movimiento
     *
     * @param habitacionActual la habitacion en la que se encuentra el usuario
     * @return la nueva habitacion en la que se encuentra el usuario
     */
    private static int irALaIzquierda(int habitacionActual) {
        if (habitacionActual != 0) { //mientras no se la posicion (indice) 0, se puede realizar el movimiento
            habitacionActual--;
            System.out.println("Te has movido a la izquierda, " + habitaciones[habitacionActual]);
        } else {
            System.out.println("Estas en la habitacion de las cajas, no hay nada mas a la izquierda");
        }

        return habitacionActual;
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
            }
        }

    }
}