package com.dedalus.oas;

import com.dedalus.oas.environments.*;
import org.apache.http.client.HttpClient;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MainDedalusInternal {

    private static OasChecker oasChecker;

    public static void main(String... args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, JAXBException, IOException {
        System.out.println("REGION,ORBIS_VERSION,APP_NAME,URL,BEARER_TOKEN_BEHAVIOUR");
        HttpClient httpClient = HttpUtils.trustEveryoneClient();
        OrbisEnvironmentsService orbisEnvironmentsService = new OrbisEnvironmentsService(new OrbisEnvironmentsClient(httpClient));
        oasChecker = new OasChecker(new OasMonitorClient(httpClient));


        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (EnvClassifier envClassifier : EnvClassifier.values()) {
            for (EnvironmentType environmentType : orbisEnvironmentsService.getEnvironments(envClassifier).getEnvironmentTypes()) {
                for (Environment environment : environmentType.getEnvironments()) {
                    for (Software software : environment.getSoftwareInstallations().getSoftwareList()) {
                        if (software.isOas()) {
                            futures.add(CompletableFuture.runAsync(() -> check(envClassifier, environment, software)));
                        }
                    }
                }
            }
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        }
    }

    private static void check(EnvClassifier envClassifier, Environment environment, Software software) {
        String bearerBehaviour;
        String host = software.getServer();
        int port = 8443 + software.getPort();
        String url = String.format("%s:%d", host, port);
        try {
            bearerBehaviour = oasChecker.acceptsTokenWithInvalidSignature(url) ?
                    "BROKEN" : "OK";
        } catch (Exception e) {
            bearerBehaviour = "UNREACHABLE";
        }
        synchronized (System.out) {
            System.out.println(String.join(",", envClassifier.name(), environment.getRegion(), environment.getOrbisVersion(), software.getName(), url, bearerBehaviour));
        }
    }
}

