package serializer;

import com.google.gson.*;
import dao.CourseDAO;

import java.lang.reflect.Type;

public class CourseDAOSerializer implements JsonSerializer<CourseDAO> {
    @Override
    public JsonElement serialize(CourseDAO course, Type type, JsonSerializationContext jsonSerializationContext) {
        System.out.println(type.getTypeName());
        JsonObject object = new JsonObject();
        object.addProperty("code", course.getCode());
        object.addProperty("name", course.getTitle());
        object.add("course_sessions", new Gson().toJsonTree(course.getCourseSessions()));
        return object;
    }
}
