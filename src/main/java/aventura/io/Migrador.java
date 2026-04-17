package aventura.io;

import aventura.domain.AventuraConfig;
import aventura.domain.Habitacion;
import aventura.domain.Objeto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

public class Migrador {

    private Gson gson;

    public Migrador(){
        gson = new GsonBuilder().registerTypeAdapter(Objeto.class, new ObjetoAdapter()).setPrettyPrinting().create();
    }

    public void migrar(AventuraConfig aventuraConfig){
        String json = gson.toJson(aventuraConfig);

        Properties prop = new Properties();

        try (BufferedReader lector = Files.newBufferedReader(Path.of("config.properties"))) {
            prop.load(lector);

            Path directorioBase = Path.of(prop.getProperty("juego.directorio.base"));
            String nombreFichero = prop.getProperty("juego.archivo.base");
            Path ficheroJson = directorioBase.resolve(nombreFichero);

            try {
                Files.writeString(ficheroJson, json);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
