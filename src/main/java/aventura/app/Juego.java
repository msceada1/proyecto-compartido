package aventura.app;

import domain.*;
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

    private void abrirContenedor(String nombreContenedor, Habitacion habitacion, Jugador jugador) {
        Objeto elObjetoAAbrir = elObjetoEstaEnLaHabitacion(nombreContenedor, habitacion);

        if (elObjetoAAbrir == null) {
            System.out.println("En el " + habitacion + " no hay " + nombreContenedor);
            return;
        }

        if (!(elObjetoAAbrir instanceof Abrible)) {
            System.out.println("El " + nombreContenedor + " no se puede abrir");
            return;
        }

        Llave llaveDelJugador = getLlaveInventario(jugador);

        Contenedor contenedor = (Contenedor) elObjetoAAbrir;

        if (contenedor.getCodigo() == null) {
            System.out.println("Has abierto " + contenedor.getNombre() + ". Objetos contenidos:\n " + Arrays.toString(contenedor.getListaDeObjetosContenidos()));
            return;
        }

        if (llaveDelJugador == null) {
            System.out.println("No tienes llave en tu inventario");
            return;
        }

        if (contenedor.getCodigo() != null && !contenedor.getCodigo().equalsIgnoreCase(llaveDelJugador.getCodigoDeSeguridad())) {
            System.out.println("El código de la llave que posees no es valido para " + contenedor.getNombre());
            return;
        }

        System.out.println("Has abierto " + contenedor.getNombre() + " objetos contenidos:\n " + Arrays.toString(contenedor.getListaDeObjetosContenidos()));
    }

    private Llave getLlaveInventario(Jugador jugador) {
        for (int i = 0; i < jugador.getInventario().length; i++) {
            if (jugador.getInventario()[i] instanceof Llave) {
                return (Llave) jugador.getInventario()[i];
            }
        }
        return null;
    }

    /**
     * Metodo que accede al inventario del jugador y lo imprime por consola.
     *
     * @param jugador el jugador del juego
     */
    private void verInventario(Jugador jugador) {
        System.out.println("Inventario: " + Arrays.toString(jugador.getInventario()));
    }

    private void mirar(Habitacion habitacion) {
        System.out.println("Te encuentras en " + habitacion.getNombre() + " y alrededor observas " + Arrays.toString(habitacion.getListaDeObjetos()));
    }

    private String examinar(String nombreDelObjetoAExaminar, Objeto[] inventario, Habitacion habitacion) {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null && inventario[i].getNombre().equalsIgnoreCase(nombreDelObjetoAExaminar)) {
                return inventario[i].getDescripcion();
            }
        }

        if (elObjetoEstaEnLaHabitacion(nombreDelObjetoAExaminar, habitacion) != null) {
            if (elObjetoEstaEnLaHabitacion(nombreDelObjetoAExaminar, habitacion).getNombre().equalsIgnoreCase(nombreDelObjetoAExaminar)) {
                return elObjetoEstaEnLaHabitacion(nombreDelObjetoAExaminar, habitacion).getDescripcion();
            }
        }

        return "El objeto " + nombreDelObjetoAExaminar + " no se ha encontrado";
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

    /**
     * Metodo que se encarga de añadir el objeto al inventario del jugador de la partida realizando llamadas a otros
     * metodos para comprobar los requisitos necesarios
     *
     * @param nombreDelObjetoACoger el nombre del {@link Objeto} que el jugador quiere añadir a su inventario
     * @param habitacion            la {@link Habitacion}  en la que se encuentra el jugador
     * @param jugador               el {@link Jugador} de la partida
     */
    private void cogerObjeto(String nombreDelObjetoACoger, Habitacion habitacion, Jugador jugador) {
        if (elObjetoEstaEnLaHabitacion(nombreDelObjetoACoger, habitacion) == null) { //se comprueba si el objeto devuelto es o no es null
            System.out.println("El objeto " + nombreDelObjetoACoger + " no se encuentra en el " + habitacion.getNombre());
            return;
        }

        //si el metodo elObjetoEstaEnLaHabitacion devuelve el objeto deseado, se instancia en una nueva variable
        Objeto objetoParaInventario = elObjetoEstaEnLaHabitacion(nombreDelObjetoACoger, habitacion);

        if (!esUnObjetoInventariable(objetoParaInventario)) { //se comprueba que el objeto sea Inventariable
            System.out.println("No puedes equipar el objeto " + objetoParaInventario.getNombre());
            return;
        }

        if (elInventarioEstaLleno(jugador)) { //se comprueba si el inventario está lleno
            System.out.println("El inventario está lleno, no puedes equipar nada más");
            return;
        }

        //si el inventario no está lleno se instancia un nuevo inventario que será igual al que tenia el jugador
        Objeto[] inventarioActualizado = jugador.getInventario();

        for (int i = 0; i < inventarioActualizado.length; i++) {
            if (inventarioActualizado[i] == null) {
                inventarioActualizado[i] = objetoParaInventario;
                jugador.setInventario(inventarioActualizado); //se actualiza el inventario con el instanciado antes
                eliminarObjetoDeLaHabitacion(objetoParaInventario, habitacion); //se elimina el objeto de la habitacion
                System.out.println("Has añadido " + nombreDelObjetoACoger + " a tu inventario");
                return;
            }
        }
    }

    /**
     * Metodo encargado de comprobar y devolver si en la habitacion en la que se encuentra el jugador de la partida está
     * el objeto que desea añadir a su inventario
     *
     * @param nombreDelObjetoACoger el objeto que el jugador quiere añadir a su inventario
     * @param habitacion            la habitacion en la que se encuentra el jugador
     * @return null si no se encontró el objeto, o, el objeto deseado en caso contrario
     */
    private Objeto elObjetoEstaEnLaHabitacion(String nombreDelObjetoACoger, Habitacion habitacion) {
        for (int i = 0; i < habitacion.getListaDeObjetos().length; i++) {
            if (habitacion.getListaDeObjetos()[i] != null) {
                if (habitacion.getListaDeObjetos()[i].getNombre().equalsIgnoreCase(nombreDelObjetoACoger)) {
                    return habitacion.getListaDeObjetos()[i]; //se devuelve el objeto si se ha encontrado
                }
            }
        }
        return null;
    }

    /**
     * Metodo encargado de comprobar si objeto que el jugador desea añadir a su inventario es {@link Inventariable}.
     *
     * @param objetoParaElInventario el {@link Objeto} que el jugador desea añadir a su inventario
     * @return {@code true} en caso afirmativo y {@code false} en caso contrario
     */
    private boolean esUnObjetoInventariable(Objeto objetoParaElInventario) {
        return objetoParaElInventario instanceof Inventariable;
    }

    /**
     * Metodo encargado de comprobar que el inventario esté o no lleno
     *
     * @param jugador el {@link Jugador} de la partida
     * @return {@code true} sin encuentra un hueco null, y, {@code false} en caso de que no haya null
     */
    private boolean elInventarioEstaLleno(Jugador jugador) {
        for (int i = 0; i < jugador.getInventario().length; i++) {
            if (jugador.getInventario()[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo encargado de eliminar el objeto de la habitacion una vez que el jugador lo haya añadido
     * a su inventario
     *
     * @param objeto     el {@link Objeto} que se elimina de la habitación
     * @param habitacion la {@link Habitacion} en la que se encuentra el usuario
     */
    private void eliminarObjetoDeLaHabitacion(Objeto objeto, Habitacion habitacion) {
        //se instancia una nueva lista de objetos que es igual a las que tiene la habitacion
        Objeto[] objetosHabitacion = habitacion.getListaDeObjetos();

        for (int i = 0; i < objetosHabitacion.length; i++) {
            if (objetosHabitacion[i] == objeto) {
                objetosHabitacion[i] = null;
                habitacion.setListaDeObjetos(objetosHabitacion);//se actualiza la lista de objetos de la habitacion con la instanciada anteriormente
                return;
            }
        }
    }

    //a partir de aquí empieza el código de la fase 2
    private void inicializarJuego() throws EntidadException {

        //Listas de objetos que tiene cada habitacion
        Latigo latigo = new Latigo("Latigo", "Cuerda larga para alcanzar objetos a largas distancias", true);
        Hoz hoz = new Hoz("Hoz", "Herramienta agrícola para segar", false);
        Palo palo = new Palo("Palo", "Instrumento de madera", true);
        Nota nota1 = new Nota("Nota 1", "Papel con información", true, "El caballo posee la herramienta, mas esta abre un tesoro en el origen");
        Objeto[] objetosEstanteria = {nota1, palo};
        Contenedor estanteria = new Contenedor("Estantería", "Mueble con capacidad para objetos", null, objetosEstanteria, true);
        Objeto[] objetosCobertizo = {hoz};
        Contenedor cobertizo = new Contenedor("Cobertizo", "Espacio donde se guardan herramientas", null, objetosCobertizo, true);
        Revolver revolver = new Revolver("Revolver", "Arma de fuego", 6, true);
        Llave llave = new Llave("Llave de Lucky Lucke", "Utensilio para abrir objetos", "1234", true);
        Objeto[] objetosAlforja = {llave};
        Alforja alforja = new Alforja("Alforja", "Bolsas que se equipan a los caballos para guardar cosas", objetosAlforja, true);
        Nota notaDeEscape = new Nota("Nota del escape", "Nota que contiene la pista para escapar", true, "En la intersección se encuentra la maquina del tiempo, busca detrás de aquello que te dió información al empezar");
        Objeto[] listaObjetosContenedorAlmacen = {notaDeEscape};
        Contenedor contenedor = new Contenedor("Cofre del tesoro", "Cofre que guarda el secreto a la salida", "1234", listaObjetosContenedorAlmacen, true);
        Mueble estatua = new Mueble("Estatua de Buda", "Monumento a la religion budista", true);

        Objeto[] listaDeObjetosDelAlmacen = {revolver, contenedor};
        Objeto[] listaDeObjetosDelPasilloPrincipal = {estanteria, estatua};
        Objeto[] listaDeObjetosDelEstablo = {latigo, cobertizo, alforja,};

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
            } else if (respuesta.equalsIgnoreCase("Coger objeto")) {
                String objeto = MiEntradaSalida.leerCadena("Qué objeto deseas coger?");
                cogerObjeto(objeto, habitaciones[jugador.getPosicion()], jugador);
            } else if (respuesta.equalsIgnoreCase("Mirar")) {
                mirar(habitaciones[jugador.getPosicion()]);
            } else if (respuesta.equalsIgnoreCase("Examinar")) {
                String objetoAExaminar = MiEntradaSalida.leerCadena("¿Que objeto deseas examinar?");
                System.out.println(examinar(objetoAExaminar, jugador.getInventario(), habitaciones[jugador.getPosicion()]));
            }
        }

    }
}