package com.dedalus.oas;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.text.MessageFormat;

public class OasMonitorClient {

    private final HttpClient httpClient;

    private static final String RESOURCES_INFO_URL_TEMPLATE = "https://{0}/oas-monitor/resources/info";

    public OasMonitorClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse getResourcesInfo(String host, String authorizationHeader) throws IOException {
        String url = MessageFormat.format(RESOURCES_INFO_URL_TEMPLATE, host);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("accept", "application/json");
        httpGet.setHeader("Authorization", authorizationHeader);
        return httpClient.execute(httpGet);
    }

}
