package Santos.David.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        System.out.println("Serializer executado");

        String gender = (String) value;

        String formattedGender = "Male".equalsIgnoreCase(gender) ? "M" : "F";

        gen.writeString(formattedGender);
    }
}
