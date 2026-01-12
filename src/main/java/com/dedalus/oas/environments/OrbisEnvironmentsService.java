package com.dedalus.oas.environments;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

public class OrbisEnvironmentsService {

    private final OrbisEnvironmentsClient orbisEnvironmentsClient;

    public OrbisEnvironmentsService(OrbisEnvironmentsClient orbisEnvironmentsClient) {
        this.orbisEnvironmentsClient = orbisEnvironmentsClient;
    }

    public Environments getEnvironments(EnvClassifier envClassifier) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Environments.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Environments) unmarshaller.unmarshal(orbisEnvironmentsClient.getEnvironments(envClassifier).getEntity().getContent());
    }
}
