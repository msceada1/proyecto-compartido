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

    private static String descripcionJuego = "Estas en clase y el profesor te manda a hacer una fotocopia. " +
            "Pero de repente pulsas el boton de imprimir y resultaba ser una maquina del tiempo ." +
            " y has despertado en un almacen lleno de cajas en las epoca del lejano oeste";

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
     * Metodo encargado de mostrar los objetos que se encuentran en el interior de un contenedor realizando
     * comprobaciones en base a unos criterios
     *
     * @param nombreContenedor el contenedo a abrir
     * @param habitacion       la habitacion en la que se encuentra el jugador
     * @param jugador          el {@link Jugador} de la partida
     */
    private void abrirContenedor(String nombreContenedor, Habitacion habitacion, Jugador jugador) {
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
     * @param nombreDelObjetoAExaminar el objeto cuya descripcion desea conocer el jugador
     * @param inventario               el inventario del jugador
     * @param habitacion               la habitacion en la que se encuentra el jugador
     * @return la descripcion del objeto
     */
    private String examinar(String nombreDelObjetoAExaminar, Objeto[] inventario, Habitacion habitacion) {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null && inventario[i].getNombre().equalsIgnoreCase(nombreDelObjetoAExaminar)) {
                if (inventario[i] instanceof Leible l) {
                    l.leer();
                }
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
                "examinar", "combinar"};

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
        //instaciamos un objeto que sera el que el jugador desea coger
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


    //private void combinar(Jugador jugador){
        //verInventario(jugador);
        //String nombreDeObjeto1 = MiEntradaSalida.leerCadena("¿Que objeto quieres combinar?");


    //}

    //a partir de aquí empieza el código de la fase 2
    private void inicializarJuego() throws EntidadException {

        //instancias datos primera habitacion
        Revolver revolver = new Revolver("Revolver", "Arma de fuego", 6, true);
        Nota notaDeEscape = new Nota("Nota del escape", "Nota que contiene la pista para escapar", true, "En la intersección se encuentra la maquina del tiempo, busca detrás de aquello que te dió información al empezar");
        Objeto[] listaObjetosCofreDelTesoroDelAlmacen = {notaDeEscape};
        Contenedor cofreDelTesoro = new Contenedor("Cofre del tesoro", "Cofre que guarda el secreto a la salida", "1234", listaObjetosCofreDelTesoroDelAlmacen, true);
        Objeto[] listaDeObjetosDelAlmacen = {revolver, cofreDelTesoro};
        Habitacion almacen = new Habitacion("Almacén", "Esto es el almacen, una pequeña habitación pero con varios misterios alrededor", listaDeObjetosDelAlmacen);

        //instancias datos segunda habitacion
        Palo palo = new Palo("Palo", "Instrumento de madera", true);
        Nota nota1 = new Nota("Nota 1", "Papel con información", true, "El caballo posee la herramienta, mas esta abre un tesoro en el origen");
        Objeto[] objetosEstanteria = {nota1, palo};
        Contenedor estanteria = new Contenedor("Estanteria", "Mueble con capacidad para objetos", null, objetosEstanteria, true);
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
                System.out.println("Inventario: ");
                verInventario(jugador);
            } else if (respuesta.equalsIgnoreCase("Coger objeto")) {
                String objeto = MiEntradaSalida.leerCadena("Qué objeto deseas coger?");
                cogerObjeto(objeto, habitaciones[jugador.getPosicion()], jugador);
            } else if (respuesta.equalsIgnoreCase("Mirar")) {
                mirar(habitaciones[jugador.getPosicion()]);
            } else if (respuesta.equalsIgnoreCase("Examinar")) {
                String objetoAExaminar = MiEntradaSalida.leerCadena("¿Que objeto deseas examinar?");
                System.out.println(examinar(objetoAExaminar, jugador.getInventario(), habitaciones[jugador.getPosicion()]));
            } else if (respuesta.equalsIgnoreCase("Abrir")) {
                String objetoAAbrir = MiEntradaSalida.leerCadena("¿Que quieres abrir?");
                abrirContenedor(objetoAAbrir, habitaciones[jugador.getPosicion()], jugador);
            } else if (respuesta.equalsIgnoreCase("Salir")) {
                System.out.println("¡Gracias por jugar!");
                probando = false;
            }
        }

    }
}