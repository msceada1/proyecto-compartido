package aventura.domain;

import java.nio.file.Path;


public class CargadorAventura {
    private Gson gson;
    private Path ficheroProperty;
    private Path  ficheroJson;
    private Path directorioBase;

    public CargadorAventura(String rutaArchivo){
        this.gson = new Gson();
    }
}
