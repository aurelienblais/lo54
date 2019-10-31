package serializer;

import com.google.gson.*;
import entity.CourseEntity;

import java.lang.reflect.Type;

public class CourseEntitySerializer implements JsonSerializer<CourseEntity> {
    @Override
    public JsonElement serialize(CourseEntity course, Type type, JsonSerializationContext jsonSerializationContext) {
        System.out.println(type.getTypeName());
        JsonObject object = new JsonObject();
        object.addProperty("code", course.getCode());
        object.addProperty("name", course.getTitle());
        object.add("course_sessions", new Gson().toJsonTree(course.getCourseSessions()));
        return object;
    }
}
