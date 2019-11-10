package serializer;

import com.google.gson.*;
import entity.CourseEntity;

import java.lang.reflect.Type;

public class CourseEntitySerializer implements JsonSerializer<CourseEntity> {
    @Override
    public JsonElement serialize(CourseEntity course, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("code", course.getCode());
        object.addProperty("title", course.getTitle());
        object.add("courseSessions", new Gson().toJsonTree(course.getCourseSessions()));
        return object;
    }
}
