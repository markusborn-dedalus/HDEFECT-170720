package com.dedalus.oas.environments;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public class OrbisEnvironmentsClient {

    private static final String ENVIRONMENTS_SPEC = "https://environments.orbis.dedalus.com/xml/environments.xml";
    private final HttpClient httpClient;

    public OrbisEnvironmentsClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse getEnvironments() throws IOException {
        HttpGet request = new HttpGet(ENVIRONMENTS_SPEC);
        return httpClient.execute(request);
    }
}
