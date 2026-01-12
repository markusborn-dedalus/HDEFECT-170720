package com.dedalus.oas.environments;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Environment {

    private String region;

    @XmlElement(name = "orbis_version")
    private String orbisVersion;

    private String db;

    public String getRegion() {
        return region;
    }

    public String getOrbisVersion() {
        return orbisVersion;
    }

    public String getDb() {
        return db;
    }

    public String getRepository() {
        return repository;
    }

    public String getSolutionBuild() {
        return solutionBuild;
    }

    public String getComments() {
        return comments;
    }

    public String getDbServer() {
        return dbServer;
    }

    public int getDbPort() {
        return dbPort;
    }

    public SoftwareInstallations getSoftwareInstallations() {
        return softwareInstallations;
    }

    private String repository;

    @XmlElement(name = "solution_build")
    private String solutionBuild;

    private String comments;

    @XmlElement(name = "db_server")
    private String dbServer;

    @XmlElement(name = "db_port")
    private int dbPort;

    @XmlElement(name = "software_installations")
    private SoftwareInstallations softwareInstallations;
}
