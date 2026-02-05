package aventura.app;

import domain.*;
import exceptions.CombinarException;
import exceptions.EntidadException;
import io.MiEntradaSalida;

import java.util.Arrays;

public class Juego {

    private static final int INDICE_PRIMERA_HABITACION = 0;
    boolean jugando = true; //variable que mantendra la partida en ejecucion


    public static void main(String[] args) {

        Juego juego = new Juego();

        try {
            juego.inicializarJuego();
        } catch (EntidadException e) {
            e.getMessage();
        }
    }

    private static String descripcionJuego = "Estás en clase y el profesor te manda a hacer unas fotocopias, \n" +
            "pero pulsas el botón de imprimir y resulta que la impresora es una máquina del tiempo. \n" +
            "Has despertado en un almacén lleno de cajas en la época del lejano oeste.";

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
            System.out.println("Te has movido al " + habitaciones[jugador.getPosicion()]);
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
            System.out.println("Te has movido al " + habitaciones[jugador.getPosicion()]);
        } else {
            System.out.println("A la izquierda del " + habitaciones[jugador.getPosicion()].getNombre() + " no hay nada");
        }
    }

    /**
     * Metodo que devuelve el texto informativo que posee un objeto
     *
     * @param inventario el el inventario del jugador
     */
    private void leer(Objeto[] inventario) {
        String objetoALeer = MiEntradaSalida.leerCadena("¿Qué quieres leer?");

        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null && inventario[i].getNombre().equalsIgnoreCase(objetoALeer)) {
                if (inventario[i] instanceof Leible) {
                    System.out.println(((Leible) inventario[i]).leer());
                    return;
                } else {
                    System.out.println("El objeto " + inventario[i].getNombre() + " no se puede leer");
                    return;
                }
            }
        }
        System.out.println("El objeto " + objetoALeer + " no se ha encontrado en el inventario");
    }

    /**
     * Metodo encargado de mostrar los objetos que se encuentran en el interior de un contenedor realizando
     * comprobaciones en base a unos criterios
     *
     * @param habitacion la habitacion en la que se encuentra el jugador
     * @param jugador    el {@link Jugador} de la partida
     */
    private void abrirContenedor(Habitacion habitacion, Jugador jugador) {
        String nombreContenedor = MiEntradaSalida.leerCadena("¿Qué deseas abrir?");

        //se instancia un nuevo Objeto que es el que el jugador desea abrir
        Objeto elObjetoAAbrir = getObjetoHabitacion(nombreContenedor, habitacion);

        //se comprueba si es o no null
        if (elObjetoAAbrir == null) {
            System.out.println("En el " + habitacion + " no hay " + nombreContenedor);
            return;
        }

        //se comprueba si es abrible
        if (!(elObjetoAAbrir instanceof Abrible)) {
            System.out.println("El " + nombreContenedor + " no se puede abrir");
            return;
        }

        //se instancia una llave la cual corresponde a la que el jugador tiene en su inventario
        Llave llaveDelJugador = getLlaveInventario(jugador);

        //se hace un cast al objeto a abrir (pues corresponde con un contenedor)
        Contenedor contenedor = (Contenedor) elObjetoAAbrir;

        //si el codigo del contenedor es null se muestra su interior
        if (contenedor.getCodigo() == null) {
            System.out.println("Has abierto " + contenedor.getNombre() + ". Objetos contenidos:\n " + Arrays.toString(contenedor.getListaDeObjetosContenidos()));

            for (int i = 0; i < contenedor.getListaDeObjetosContenidos().length; i++) {
                jugador.addObjetoInventario(contenedor.getListaDeObjetosContenidos()[i]);
            }

            contenedor.eliminarObjetos();

            System.out.println("Se han guardado los objetos en el inventario");

            return;
        }

        //se comprueba si el jugador tiene llave en su inventario
        if (llaveDelJugador == null) {
            System.out.println("No tienes llave en tu inventario");
            return;
        }

        //se comprueba que los codigos sean iguales
        if (contenedor.getCodigo() != null && !contenedor.getCodigo().equalsIgnoreCase(llaveDelJugador.getCodigoDeSeguridad())) {
            System.out.println("El código de la llave que posees no es valido para " + contenedor.getNombre());
            return;
        }

        //llegados aqui se han pasado todas las comprobaciones por lo que se muestra el contenido del contendor que tiene codigo
        System.out.println("Has abierto " + contenedor.getNombre() + " objetos contenidos:\n " + Arrays.toString(contenedor.getListaDeObjetosContenidos()));

        for (int i = 0; i < contenedor.getListaDeObjetosContenidos().length; i++) {
            jugador.addObjetoInventario(contenedor.getListaDeObjetosContenidos()[i]);
        }

        contenedor.eliminarObjetos();

        System.out.println("Se han guardado los objetos en el inventario");


    }

    /**
     * Metodo encargado de devolver la llave que el jugador posee en su inventario
     *
     * @param jugador el jugador del juego
     * @return {@link Llave} si encuentra la llave o null en caso contrario
     */
    private Llave getLlaveInventario(Jugador jugador) {
        for (int i = 0; i < jugador.getInventario().length; i++) {
            if (jugador.getInventario()[i] != null) {
                if (jugador.getInventario()[i] instanceof Llave llave) {
                    return llave;
                }
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
        System.out.println("Inventario:");

        for (int i = 0; i < jugador.getInventario().length; i++) {
            if (jugador.getInventario()[i] != null) {
                System.out.println(jugador.getInventario()[i]);
            }
        }
    }

    /**
     * Metodo encargado de mostrar en que habitacion se encuentra el jugador
     *
     * @param habitacion la habitacion en la que se encuentra el jugador
     */
    private void mirar(Habitacion habitacion) {
        System.out.println("Te encuentras en " + habitacion.getNombre() + " y alrededor observas " + Arrays.toString(habitacion.getListaDeObjetos()));
    }

    /**
     * Metodo encargado de mostrar la descripcion del objeto que el jugador desea
     *
     * @param inventario el inventario del jugador
     * @param habitacion la habitacion en la que se encuentra el jugador
     * @return la descripcion del objeto
     */
    private String examinar(Objeto[] inventario, Habitacion habitacion) {
        String nombreDelObjetoAExaminar = MiEntradaSalida.leerCadena("¿Qué objeto deseas examinar?");

        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null && inventario[i].getNombre().equalsIgnoreCase(nombreDelObjetoAExaminar)) {
                return inventario[i].getDescripcion();
            }
        }

        Objeto elObjetoEncontrado = getObjetoHabitacion(nombreDelObjetoAExaminar, habitacion);

        if (elObjetoEncontrado != null) {
            if (elObjetoEncontrado.getNombre().equalsIgnoreCase(nombreDelObjetoAExaminar)) {
                return elObjetoEncontrado.getDescripcion();
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

        String[] comandos = {"ayuda", "mirar", "ir izquierda", "ir derecha", "inventario", "coger objeto", "salir", "abrir",
                "examinar", "combinar", "leer", "mover", "combinar"};

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
     * @param habitacion la {@link Habitacion}  en la que se encuentra el jugador
     * @param jugador    el {@link Jugador} de la partida
     */
    private void cogerObjeto(Habitacion habitacion, Jugador jugador) {
        //instaciamos un objeto que sera el que el jugador desea coger

        String nombreDelObjetoACoger = MiEntradaSalida.leerCadena("¿Qué objeto deseas coger?");

        Objeto objetoParaInventario = getObjetoHabitacion(nombreDelObjetoACoger, habitacion);

        if (objetoParaInventario == null) { //se comprueba si el objeto devuelto es o no es null
            System.out.println("El objeto " + nombreDelObjetoACoger + " no se encuentra en el " + habitacion.getNombre());
            return;
        }

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
    private Objeto getObjetoHabitacion(String nombreDelObjetoACoger, Habitacion habitacion) {
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

    /**
     * Metodo que mueve los objetod que hay en el mapa
     *
     * @param habitacion la habitacion en la que se encuentra el jugador
     * @param j          el jugador de la partida
     */
    private void mover(Habitacion habitacion, Jugador j) {
        String objetoAMover = MiEntradaSalida.leerCadena("¿Qué quieres mover?");
        Objeto objetoHab = getObjetoHabitacion(objetoAMover, habitacion);
        Nota notaEscape = (Nota) getObjetoInventario(j, "Nota de escape");

        if (objetoHab == null) {
            System.out.println("El objeto " + objetoAMover + " no se encuentra en " + habitacion.getNombre());
            return;
        }

        if (!(objetoHab instanceof Movible)) {
            System.out.println("No puedes mover " + objetoHab.getNombre());
            return;
        }

        if (notaEscape != null) {
            System.out.println("Has movido " + objetoHab.getNombre() + " ves la máquina del tiempo y vuelves a tu época." +
                    "¡Enhorabuena por completar el juego!");
            jugando = false;
            return;
        }

        System.out.println("No puedes mover " + objetoHab.getNombre() + " necesitas una nota indicativa");
        return;
    }


    /**
     * Metodo que busca un objeto en el inventario del jugador
     *
     * @param j            el jugador de la partida
     * @param nombreObjeto el nombre del objeto que se desea devolver
     * @return null si no encuentra el objeto o en caso contrario el {@link Objeto} deseado
     */
    private Objeto getObjetoInventario(Jugador j, String nombreObjeto) {
        for (int i = 0; i < j.getInventario().length; i++) {
            if (j.getInventario()[i] != null && j.getInventario()[i].getNombre().equalsIgnoreCase(nombreObjeto)) {
                return j.getInventario()[i];
            }
        }
        return null;
    }

    /**
     * Metodo encargado de combinar dos objetos en uno, elminando los combinados del inevntario y dejando
     * solo el resultante
     *
     * @param j el jugador del juego
     * @throws EntidadException  excepcion heredera de clases padres
     * @throws CombinarException si los objetos no se pueden combinar
     */
    private void combinar(Jugador j) throws EntidadException, CombinarException {
        String objeto1 = MiEntradaSalida.leerCadena("¿Qué objeto quieres combinar?");
        Objeto objetoACombinar1 = getObjetoInventario(j, objeto1);

        if (objetoACombinar1 == null) {
            System.out.println("No posees " + objeto1 + " en tu inventario");
            return;
        }

        if (!(objetoACombinar1 instanceof Combinable)) {
            System.out.println("El objeto " + objetoACombinar1.getNombre() + " no se puede combinar con nada");
            return;
        }

        String objeto2 = MiEntradaSalida.leerCadena("¿Con que objeto quieres combinar " + objetoACombinar1.getNombre() + "?");
        Objeto objetoACombinar2 = getObjetoInventario(j, objeto2);

        if (objetoACombinar2 == null) {
            System.out.println("No posees " + objeto2 + " en tu inventario");
            return;
        }

        if (!(objetoACombinar2 instanceof Combinable)) {
            System.out.println("El objeto " + objetoACombinar2.getNombre() + " no se puede combinar con nada");
        }

        //una vez llegados aquí significa que se han pasado las comprobaciones. Por tanto los objetos son combinables

        Guadania guadaniaOriginada = (Guadania) ((Combinable) objetoACombinar1).combinar(objetoACombinar2);

        //se añade el objeto resultante al inventario y se eliminan los combinados
        j.addObjetoInventario(guadaniaOriginada);
        j.eliminarObjetoInventario(objetoACombinar1);
        j.eliminarObjetoInventario(objetoACombinar2);

        System.out.println("Se ha añadido " + guadaniaOriginada.getNombre() + " al inventario");
    }

    //a partir de aquí empieza el código de la fase 2
    private void inicializarJuego() throws EntidadException {

        //instancias datos primera habitacion
        Revolver revolver = new Revolver("Revolver", "Arma de fuego", 6, true);
        Nota notaDeEscape = new Nota("Nota de escape", "Nota que contiene la pista para escapar", true, "En la intersección se encuentra la maquina del tiempo, busca detrás de aquello que te dió información al empezar");
        Objeto[] listaObjetosCofreDelTesoroDelAlmacen = {notaDeEscape};
        Contenedor cofreDelTesoro = new Contenedor("Cofre del tesoro", "Cofre que guarda el secreto a la salida", "1234", listaObjetosCofreDelTesoroDelAlmacen, true);
        Objeto[] listaDeObjetosDelAlmacen = {revolver, cofreDelTesoro};
        Habitacion almacen = new Habitacion("Almacén", "Esto es el almacen, una pequeña habitación pero con varios misterios alrededor", listaDeObjetosDelAlmacen);

        //instancias datos segunda habitacion
        Palo palo = new Palo("Palo", "Instrumento de madera", true);
        Nota nota1 = new Nota("Nota 1", "Papel con información", true, "El caballo posee la herramienta, mas esta abre un tesoro en el origen");
        Objeto[] objetosEstanteria = {nota1, palo};
        Estanteria estanteria = new Estanteria("Estanteria", "Mueble con capacidad para objetos", null, objetosEstanteria, true);
        Mueble estatua = new Mueble("Estatua de Buda", "Monumento a la religion budista", true);
        Objeto[] listaDeObjetosDelPasilloPrincipal = {estanteria, estatua};
        Habitacion pasilloPrincipal = new Habitacion("Pasillo principal", "Esto es el pasillo principal, un pasillo largo y frío, tiene unas estanterías bastante llamativas", listaDeObjetosDelPasilloPrincipal);

        //instancias datos tercera habitacion
        Latigo latigo = new Latigo("Latigo", "Cuerda larga para alcanzar objetos a largas distancias", true);
        Hoz hoz = new Hoz("Hoz", "Herramienta agrícola para segar", false);
        Objeto[] objetosCobertizo = {hoz};
        Contenedor cobertizo = new Contenedor("Cobertizo", "Espacio donde se guardan herramientas", null, objetosCobertizo, true);
        Llave llave = new Llave("Llave de Lucky Lucke", "Utensilio para abrir objetos", "1234", true);
        Objeto[] objetosAlforja = {llave};
        Contenedor alforja = new Contenedor("Alforja", "Bolsas que equipan los caballos para guardar cosas", null, objetosAlforja, true);
        Objeto[] listaDeObjetosDelEstablo = {latigo, cobertizo, alforja,};
        Habitacion establo = new Habitacion("Establo", "Esto es el establo donde se encuentran el caballo de Lucky Lucke con una preciosa montura cuyas alforjas podrían tener algo", listaDeObjetosDelEstablo);

        //instanciando array de habitaciones
        Habitacion[] habitaciones = {almacen, pasilloPrincipal, establo};

        //instancia del jugador y comienzo de la eleccion de acciones
        Jugador jugador = new Jugador();

        System.out.println(descripcionJuego);

        while (jugando) {
            System.out.println(Arrays.toString(comandos()));
            String comandoIntroducido = MiEntradaSalida.leerCadena("¿Qué acción deseas realizar?");
            while (!comandoValido(comandoIntroducido, comandos())) {
                System.out.println(Arrays.toString(comandos()));
                comandoIntroducido = MiEntradaSalida.leerCadena("Has introducido un comando incorrecto, prueba de nuevo.");
            }

            switch (comandoIntroducido) {
                case "ayuda" -> System.out.println(Arrays.toString(comandos()));
                case "mirar" -> mirar(habitaciones[jugador.getPosicion()]);
                case "inventario" -> verInventario(jugador);
                case "ir izquierda" -> irALaIzquierda(jugador, habitaciones);
                case "ir derecha" -> irALaDerecha(jugador, habitaciones);
                case "coger objeto" -> cogerObjeto(habitaciones[jugador.getPosicion()], jugador);
                case "examinar" ->
                        System.out.println(examinar(jugador.getInventario(), habitaciones[jugador.getPosicion()]));
                case "abrir" -> abrirContenedor(habitaciones[jugador.getPosicion()], jugador);
                case "salir" -> jugando = false;
                case "leer" -> leer(jugador.getInventario());
                case "mover" -> mover(habitaciones[jugador.getPosicion()], jugador);
                case "combinar" -> {
                    try {
                        combinar(jugador);
                    } catch (CombinarException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        System.out.println("¡Gracias por jugar!");
    }
}