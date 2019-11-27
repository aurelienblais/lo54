package serializer;

import com.google.gson.*;
import entity.CourseSessionEntity;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class CourseSessionEntitySerializer implements JsonSerializer<CourseSessionEntity> {
    @Override
    public JsonElement serialize(CourseSessionEntity courseSession, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("id", courseSession.getId());
        object.addProperty("startDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(courseSession.getStartDate()));
        object.addProperty("endDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(courseSession.getEndDate()));
        object.addProperty("max", courseSession.getMax());
        object.add("location", new Gson().toJsonTree(courseSession.getLocation()));
        object.add("clients", new Gson().toJsonTree(courseSession.getClients()));

        // Avoid circular references
        courseSession.getCourse().setCourseSessions(null);
        object.add("course", new Gson().toJsonTree(courseSession.getCourse()));
        return object;
    }
}
