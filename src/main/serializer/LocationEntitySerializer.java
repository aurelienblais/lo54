package serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import entity.LocationEntity;

import java.lang.reflect.Type;

public class LocationEntitySerializer implements JsonSerializer<LocationEntity> {
    @Override
    public JsonElement serialize(LocationEntity location, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("id", location.getId());
        object.addProperty("city", location.getCity());
        return object;
    }
}
