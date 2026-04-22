package aventura.domain;

import aventura.app.Juego;
import aventura.io.ObjetoAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class CargadorAventura {
    private Gson gson;
    private Path ficheroProperty;
    private Path  ficheroJson;
    private Path directorioBase;

    public CargadorAventura(){
        this.gson = new GsonBuilder().registerTypeAdapter(Objeto.class, new ObjetoAdapter()).setPrettyPrinting().create();
        this.ficheroProperty = Paths.get("config.properties");
        cargarConfiguracion();
    }

    public void cargarConfiguracion() {
        Properties prop = new Properties();

        try (BufferedReader lector = Files.newBufferedReader(this.ficheroProperty)) {
            prop.load(lector);

            this.directorioBase = Path.of(prop.getProperty("juego.directorio.base"));
            String nombreFichero = prop.getProperty("juego.archivo.base");
            this.ficheroJson = directorioBase.resolve(nombreFichero);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public AventuraConfig cargarMundoBase() {
        if (this.ficheroJson == null) return null;

        try (BufferedReader br = Files.newBufferedReader(this.ficheroJson)) {
            return gson.fromJson(br, AventuraConfig.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
