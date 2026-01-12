package com.dedalus.oas.environments;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.text.MessageFormat;

public class OrbisEnvironmentsClient {

    private static final String ENVIRONMENTS_SPEC_TEMPLATE = "https://environments.orbis.dedalus.com/xml/environments{0}.xml";
    private final HttpClient httpClient;

    public OrbisEnvironmentsClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse getEnvironments(EnvClassifier envClassifier) throws IOException {
        String url = MessageFormat.format(ENVIRONMENTS_SPEC_TEMPLATE, envClassifier.getSuffix());
        HttpGet request = new HttpGet(url);
        return httpClient.execute(request);
    }
}
