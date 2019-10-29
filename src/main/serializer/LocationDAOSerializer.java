package serializer;

import com.google.gson.*;
import dao.CourseDAO;
import dao.LocationDAO;

import java.lang.reflect.Type;

public class LocationDAOSerializer implements JsonSerializer<LocationDAO> {
    @Override
    public JsonElement serialize(LocationDAO location, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("id", location.getId());
        object.addProperty("city", location.getCity());
        return object;
    }
}
