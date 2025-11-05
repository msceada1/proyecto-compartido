package aventura.app;

import utils.MiEntradaSalida;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD1-UD3).
 * VUESTRO TRABAJO es rellenar todos los TODO
 */
public class Juego {

    // --- NÚCLEO: Definición de Datos (FASE 1) ---
    // Esta parte os la damos HECHA. Es el "contrato" del núcleo.

    private static String descripcionJuego = "El maestro te ha enviado a hacer fotocopias" +
            "y la impresora es una maquina del tiempo y has viajado a la epoca de los vaqueros" +
            "Miras a tu alrededor y es todo como una pelicula del salvaje oeste.Ahora tienes que adaptarte a la nueva epoca";

    // El mapa de habitaciones.
    // TODO: (Skin) ¡Rellenad esto con vuestras descripciones!
    private static String[] habitaciones = {
            "Estás en la habitacion de las cajas pero !SORPRESA¡ en una de las cajas hay un revolver",  // Posición 0
            "Estás en el pasillo principal. Hay puertas a la DERECHA y a la IZQUIERDA.", // Posición 1
            "Estás en el establo. Hay una puerta a la IZQUIERDA y has visto una 'latigo' en una valla.", // Posición 2
            // Borra las habitaciones y escribe las tuyas
    };

    // Los objetos que hay en cada habitación.
    // TODO: (Skin) Rellenad esto con vuestros objetos
    private static String[][] objetosMapa = {
            {null, "revolver"},           // Objetos en Habitación 0
            {null, null},           // Objetos en Habitación 1
            {null, "latigo"},      // Objetos en Habitación 2
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

            System.out.print("\n> ");

            System.out.println("Los comandos son" + Arrays.toString(comandos()));
            String comando = MiEntradaSalida.leerCadena("¿Que accion quieres hacer?");

            while (!comandoValido(comando, comandos())){
                comando = MiEntradaSalida.leerCadena("Has introducido un comando erroneo,vuelve a intentarlo");
            }

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

    private static String[] comandos() {
        String[] comandos = {"ayuda", "mirar", "ir a la izquierda", "ir a la derecha", "inventario", "coger objeto", "salir"};
        return comandos;
    }

    private static boolean comandoValido(String comando, String[] comandos) {

        for (int i = 0; i < comandos().length; i++) {
            if (comando.equalsIgnoreCase(comandos[i])) {
                return true;
            }
        }
        return false;
    }
}