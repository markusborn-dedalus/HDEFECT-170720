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
        HttpClient httpClient = HttpUtils.trustEveryoneClient();
        OrbisEnvironmentsService orbisEnvironmentsService = new OrbisEnvironmentsService(new OrbisEnvironmentsClient(httpClient));
        oasChecker = new OasChecker(new OasMonitorClient(httpClient));

        Environments environments = orbisEnvironmentsService.getEnvironments();

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (EnvironmentType environmentType : environments.getEnvironmentTypes()) {
            for (Environment environment : environmentType.getEnvironments()) {
                for (Software software : environment.getSoftwareInstallations().getSoftwareList()) {
                    if (software.isOas()) {
                        futures.add(CompletableFuture.runAsync(() -> check(environment, software)));
                    }
                }
            }
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private static void check(Environment environment, Software software) {
        String bearerBehaviour;
        try {
            String host = software.getServer();
            int port = 8443 + software.getPort();
            String url = host + ":" + port;
            bearerBehaviour = oasChecker.acceptsTokenWithInvalidSignature(url) ?
                    "BROKEN" : "OK";
        } catch (Exception e) {
            bearerBehaviour = "UNREACHABLE";
        }

        synchronized (System.out) {
            System.out.format("%-8s %-15s %-55s %-40s %-20s %n", environment.getRegion(), environment.getOrbisVersion(), software.getName(), software.getServer(), bearerBehaviour);
        }
    }

}

