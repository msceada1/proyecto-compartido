package aventura.app;

import java.util.Scanner;

/**
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD1-UD3).
 * VUESTRO TRABAJO es rellenar todos los TODO
 */
public class Juego {

    // --- NÚCLEO: Definición de Datos (FASE 1) ---
    // Esta parte os la damos HECHA. Es el "contrato" del núcleo.

    private static String descripcionJuego = "Estas en clase y el profesor te manda a hacer una fotocopia. " +
            "Pero de repente pulsas el boton de imprimir y resultaba ser una maquina del tiempo ." +
            " y has despertado en un almacen lleno de cajas en las epoca del lejano oeste";

    // El mapa de habitaciones.
    // TODO: (Skin) ¡Rellenad esto con vuestras descripciones!
    private static String[] habitaciones = {
            "Estás en el almacen lleno de cajas y hay un reluciente 'revolver' dentro de una caja abierta.",  // Posición 0
            "Has salido a la calle principal, el almacen esta a la IZQUIERDA y a la DERECHA ves un antiguo establo.", // Posición 1
            "Has entrado al establo,y ves un 'latigo' que esta como nuevo encima de una valla y tienes la calle principal a la IZQUIERDA.", // Posición 2
            // Borra las habitaciones y escribe las tuyas
    };

    // Los objetos que hay en cada habitación.
    // TODO: (Skin) Rellenad esto con vuestros objetos
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

        // TODO 1a: Muestra la descripción general del juego
        System.out.println(descripcionJuego);
        // TODO 1b: Muestra la descripción de la primera habitación
        // Pista: System.out.println(habitaciones[...]);
        System.out.println(habitaciones[0]);
        // TODO 2: Iniciar el bucle principal del juego (game loop)
        while (jugando) {

            // TODO 3: Leer el comando del usuario por teclado
            System.out.print("\n> ");
            //String comando = ...;


            /*
            TODO 4: Crear un 'switch' o una estructura 'if-else if'
             para procesar el 'comando' del usuario.
             Debe gestionar como mínimo: "ayuda", "mirar", "inventario",
             "ir derecha", "ir izquierda", "coger [objeto]" y "salir".
             */


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
     * @return Devuelve los comandos que se usaran
     */
    private static String[] comandos() {
        String[] comandos = {"ayuda", "mirar", "ir a la izquierda", "ir a la derecha", "inventario", "coger objeto", "salir"};
        return comandos;
    }

    /**
     * Metodo que comprueba que el comando introducido sea valido
     * @param comando El comando que introduce el usuario
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

}