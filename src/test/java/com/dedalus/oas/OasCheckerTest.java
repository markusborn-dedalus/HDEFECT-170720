package com.dedalus.oas;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

class OasCheckerTest {

    private static OasChecker oasChecker;

    @BeforeAll
    static void setUp() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        oasChecker = new OasChecker(new OasMonitorClient(HttpUtils.trustEveryoneClient()));
    }

    @Test
    void acceptsInvalidToken_returnsTrue_whenOasIsFromRelease8522() throws IOException {
        // given
        // This is an instance of an OAS with the scenario application:fluidmanagement in QA_FR_852200
        // This test should not rely on an external system but at the time being (01/2026) we have no mock server available
        String oasHost = "LS001071.DEDALUS.LAN:8443";

        // when
        boolean result = oasChecker.acceptsTokenWithInvalidSignature(oasHost);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void acceptsInvalidToken_returnsFalse_whenOasIsFromRelease8444() throws IOException {
        // given
        // This is an instance of an OAS with the scenario standard:single-without-medication-batch in QA_LU_844305
        // This test should not rely on an external system but at the time being (01/2026) we have no mock server available
        String oasHost = "WS008049.DEDALUS.LAN:8443";

        // when
        boolean result = oasChecker.acceptsTokenWithInvalidSignature(oasHost);

        // then
        assertThat(result).isFalse();
    }
}