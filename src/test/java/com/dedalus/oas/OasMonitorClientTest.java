package com.dedalus.oas;

import org.apache.http.HttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

class OasMonitorClientTest {

    private static OasMonitorClient oasMonitorClient;

    @BeforeAll
    static void setUp() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        oasMonitorClient = new OasMonitorClient(HttpUtils.trustEveryoneClient());
    }

    @Test
    void getResourcesInfo_returnsValidResponse_whenUsingBasicAuth() throws IOException {
        // given
        String authorizationHeader = basicAuthHeader("SCHULUNG", "schulung");

        // when
        // this test should not rely on an external system but at the time being (01/2026) we have no mock server available
        HttpResponse response = oasMonitorClient.getResourcesInfo("WS000024.DEDALUS.LAN:8443", authorizationHeader);

        // then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(JsonResponseBodyHandler.readBody(response).asJsonObject().getString("proxyUrl")).startsWith("https://");
    }

    private String basicAuthHeader(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
    }

}