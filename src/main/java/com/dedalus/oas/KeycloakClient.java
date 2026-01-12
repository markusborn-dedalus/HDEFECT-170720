package com.dedalus.oas;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

public class KeycloakClient {

    private final HttpClient httpClient;

    private static final String URL_TEMPLATE = "https://{0}/auth/realms/{1}/protocol/openid-connect/token";
    private static final String BODY_TEMPLATE=
            "grant_type=password"
            + "&client_id={0}"
            + "&username={1}"
            + "&password={2}"
            + "&scope=openid&orbis_facility_id=1";

    public KeycloakClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse getAccessToken(String host, String realm, String clientId, String username, String password) throws IOException {
        String url = MessageFormat.format(URL_TEMPLATE, host, realm);
        String body = MessageFormat.format(BODY_TEMPLATE, clientId, username, password);

        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("Accept", "application/json");
        post.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
        return httpClient.execute(post);
    }

}
