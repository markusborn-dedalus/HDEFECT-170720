package com.dedalus.oas;

import org.apache.http.HttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

class KeycloakClientTest {

    private static KeycloakClient keycloakClient;

    @BeforeAll
    static void setUp() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        keycloakClient = new KeycloakClient(HttpUtils.trustEveryoneClient());
    }


    @Test
    void getAccessToken_returnsValidAccessToken() throws IOException {
        // when
        // this test should not rely on an external system but at the time being (01/2026) we have no mock server available
        HttpResponse response = keycloakClient.getAccessToken("eol4440a-proxy.orbis.dedalus.com", "orbis", "orbis-u-webclient", "SCHULUNG", "schulung");

        // then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(JsonResponseBodyHandler.readBody(response).asJsonObject().getString("access_token")).startsWith("ey");
    }
}