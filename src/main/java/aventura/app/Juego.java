package aventura.app;

import utils.MiEntradaSalida;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD1-UD3).
 * VUESTRO TRABAJO es rellenar todos los TODO
 */
public class Juego {

    private static String descripcionJuego = "Estas en clase y el profesor te manda a hacer una fotocopia. " +
            "Pero de repente pulsas el boton de imprimir y resultaba ser una maquina del tiempo ." +
            " y has despertado en un almacen lleno de cajas en las epoca del lejano oeste";

    // El mapa de habitaciones.

    private static String[] habitaciones = {
            "Estás en el almacen lleno de cajas y hay un reluciente 'revolver' dentro de una caja abierta.",  // Posición 0
            "Has salido a la calle principal, el almacen esta a la IZQUIERDA y a la DERECHA ves un antiguo establo.", // Posición 1
            "Has entrado al establo,y ves un 'latigo' que esta como nuevo encima de una valla y tienes la calle principal a la IZQUIERDA.", // Posición 2
            // Borra las habitaciones y escribe las tuyas
    };

    private static String[][] objetosMapa = {
            {"revolver", null},           // Objetos en Habitación 0
            {null, null},           // Objetos en Habitación 1
            {"latigo", null},      // Objetos en Habitación 2
    };

    // El inventario del jugador. Tamaño fijo.
    private static String[] inventario = new String[5];

    // Variable que guarda la posición actual del jugador
    private static int habitacionActual = 0; // Empezamos en la primera habitación

    // --- FIN DE LA DEFINICIÓN DE DATOS ---


    public static void main(String[] args) {
        // Puedes utilizar la clase MiEntradaSalida, que viviría en el paquete io
        Scanner scanner = new Scanner(System.in);
        boolean jugando = true;

        System.out.println("¡Bienvenido a 'TU PROPIA AVENTURA'!");
        System.out.println("------------------------------------------");


        System.out.println(descripcionJuego);


        System.out.println(habitaciones[0]);

        while (jugando) {

            /**
             * Lee el comando que has introducido y si es distinto a los comandos devuelve el error y te manda a escribir otra vez.
             */
            System.out.print("\n> ");
            String comandoIntroducido = MiEntradaSalida.leerCadena("¿Que accion quieres hacer?");
            while (!comandoValido(comandoIntroducido, comandos())) {
                comandoIntroducido = MiEntradaSalida.leerCadena("Has introducido un comando incorrecto, prueba de nuevo.");
            }

            switch (comandoIntroducido) {
                case "ayuda" -> System.out.println(Arrays.toString(comandos()));
                case "mirar" -> System.out.println(habitaciones[habitacionActual]);
                case "inventario" -> mirarInventario(inventario);
                case "ir a la derecha" -> habitacionActual = irALaDerecha(habitacionActual);
                case "ir a la izquierda" -> habitacionActual = irALaIzquierda(habitacionActual);
                case "coger objeto" -> inventario = cogerObjeto();
                case "salir" -> jugando = false;
                default -> System.out.println(Arrays.toString(comandos()));
            }

        }

        System.out.println("¡Gracias por jugar!");
        scanner.close();
    }

    /**
     * Metodo que mueve la posicion en la que estamos a la derecha, comprobando que no este en el limite de la derecha.
     *
     * @param habitacionActual la habitacion (posicion en la que estoy)
     * @return la nueva habitacion (posicion) en la que me encuentro
     */
    private static int irALaDerecha(int habitacionActual) {

        if (habitacionActual < habitaciones.length - 1) { //se resta 1 a la longitud porque es de 0 a 2, y no salte la excepcion IndexOutBounds
            habitacionActual++;
            System.out.println("Te has movido a la derecha");
        } else {
            System.out.println("No hay nada mas allá del establo");
        }

        return habitacionActual;
    }

    /**
     * Metodo que mueve la posicion en la que estamos a la izquierda, comprobando que no este en el limite de la izquierda.
     *
     * @param habitacionActual la habitacion (posicion en la que estoy)
     * @return la nueva habitacion (posicion) en la que me encuentro
     */
    private static int irALaIzquierda(int habitacionActual) {
        if (habitacionActual != 0) {
            habitacionActual--;
            System.out.println("Te has movido a la izquierda");
        } else {
            System.out.println("Estas en la habitacion de las cajas, no hay nada mas a la izquierda");
        }

        return habitacionActual;
    }


    /**
     * Metodo que asigna los comandos que se usaran
     *
     * @return Devuelve los comandos que se usaran
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
     * @return Si el comando introducido es valido devuelve true y si es distinto devuelve false
     */
    private static boolean comandoValido(String comando, String[] comandos) {

        for (int i = 0; i < comandos().length; i++) {
            if (comando.equalsIgnoreCase(comandos[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que informa de los objetos que hay en el inventario. No devuelve nada porque solo informa
     * por pantalla al usuario.
     *
     * @param inventario El array de objetos
     */
    private static void mirarInventario(String[] inventario) {

        boolean hayObjeto = false;

        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null) { //si es ditinto de null, hay un objeto y se imprime por pantalla
                System.out.println("Posees : " + inventario[i]);
                hayObjeto = true;
            }
        }

        if (!hayObjeto) {
            System.out.println("El inventario está vacío");
        }
    }

    private static String[] cogerObjeto() {

        if (hayObjeto()) {
            String objeto = MiEntradaSalida.leerCadena("¿Que objeto quieres coger?");
            for (int i = 0; i < objetosMapa[habitacionActual].length; i++) {
                if (objeto.equalsIgnoreCase(objetosMapa[habitacionActual][i])) {
                    if (objetosMapa[habitacionActual][i] != null) {
                        if (objetosMapa[habitacionActual][i].equalsIgnoreCase(objeto)) {
                            guardarInventario(objeto);
                            objetosMapa[habitacionActual][i] = null;
                        }
                    }
                }
            }

            for (int j = 0; j < objetosMapa[habitacionActual].length; j++) {

            }

        } else {
            System.out.println("No hay objeto donde te encuentras");
        }

        return inventario;
    }

    private static void guardarInventario(String objeto) {
        int ocupado = 0;
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null) ocupado++;
        }
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) {
                inventario[i] = objeto;
                return;
            } else if (inventario != null) {
                if (ocupado == inventario.length) {
                    System.out.println("No te queda espacio en el inventario.");
                }
            }
        }
    }

    /**
     * Metodo que compruba si hay un objeto en la habitacion en la que estoy.
     *
     * @return {@code true} si hay objeto, {@code false} si no hay objeto
     */
    private static boolean hayObjeto() {

        for (int i = 0; i < objetosMapa[habitacionActual].length; i++) {
            if (objetosMapa[habitacionActual][i] != null) { //segun en la habitacion en la estamos comprobamos que en las columnas de objetosMapa haya un objetos.
                return true;
            }
        }

        return false;
    }
}