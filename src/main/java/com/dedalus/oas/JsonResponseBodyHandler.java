package com.dedalus.oas;

import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.json.JsonStructure;
import org.apache.http.HttpResponse;

import java.io.IOException;

public class JsonResponseBodyHandler {

    private JsonResponseBodyHandler() {}

    public static JsonStructure readBody(HttpResponse httpResponse) throws IOException {
        try (JsonReader jsonReader = Json.createReader(httpResponse.getEntity().getContent())) {
            return jsonReader.read();
        }
    }
}
