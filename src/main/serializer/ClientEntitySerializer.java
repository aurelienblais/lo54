package serializer;

import com.google.gson.*;
import entity.ClientEntity;

import java.lang.reflect.Type;

public class ClientEntitySerializer implements JsonSerializer<ClientEntity> {
    @Override
    public JsonElement serialize(ClientEntity client, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("id", client.getId());
        object.addProperty("firstname", client.getFirstname());
        object.addProperty("lastname", client.getLastname());
        object.addProperty("address", client.getAddress());
        object.addProperty("email", client.getEmail());
        object.addProperty("phone", client.getPhone());
        return object;
    }
}
