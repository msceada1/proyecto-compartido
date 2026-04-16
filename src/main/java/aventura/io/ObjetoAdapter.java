package aventura.io;

import aventura.domain.Objeto;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ObjetoAdapter implements JsonSerializer<Objeto>, JsonDeserializer<Objeto> {

    @Override
    public Objeto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        JsonPrimitive primitive = (JsonPrimitive) jsonObject.get("tipo");
        String className = primitive.getAsString();

        try {
            // 2. Intentamos obtener la Clase real a partir del string
            Class<?> clazz = Class.forName(className);

            // 3. Le pedimos a Gson que deserialice el JSON usando esa clase específica
            return context.deserialize(jsonObject, clazz);

        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Error al encontrar la clase para deserializar: " + className, e);
        }
    }


    @Override
    public JsonElement serialize(Objeto src, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement element = context.serialize(src);
        JsonObject object = element.getAsJsonObject();

        object.addProperty("tipo", src.getClass().getName());

        return object;
    }
}
