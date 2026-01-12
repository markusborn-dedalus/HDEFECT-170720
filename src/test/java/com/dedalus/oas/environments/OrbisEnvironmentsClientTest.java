package com.dedalus.oas.environments;

import com.dedalus.oas.HttpUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrbisEnvironmentsServiceTest {

    @ParameterizedTest
    @EnumSource(EnvClassifier.class)
    void getEnvironments_returnsExpectedContent(EnvClassifier envClassifier) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, JAXBException {
        // given
        OrbisEnvironmentsService orbisEnvironmentsService = new OrbisEnvironmentsService(new OrbisEnvironmentsClient(HttpUtils.trustEveryoneClient()));

        // when
        Environments environments = orbisEnvironmentsService.getEnvironments(envClassifier);

        // then
        assertThat(environments).isNotNull();
    }
}