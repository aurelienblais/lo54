package serializer;

import com.google.gson.*;
import dao.CourseSessionDAO;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class CourseSessionDAOSerializer implements JsonSerializer<CourseSessionDAO> {
    @Override
    public JsonElement serialize(CourseSessionDAO courseSession, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("id", courseSession.getId());
        object.addProperty("startDate", new SimpleDateFormat("dd-mm-YYYY:hh:ss").format(courseSession.getStartDate()));
        object.addProperty("endDate", new SimpleDateFormat("dd-mm-YYYY:hh:ss").format(courseSession.getEndDate()));
        object.addProperty("max", courseSession.getMax());
        object.add("location", new Gson().toJsonTree(courseSession.getLocation()));
        object.add("clients", new Gson().toJsonTree(courseSession.getClients()));
        return object;
    }
}
