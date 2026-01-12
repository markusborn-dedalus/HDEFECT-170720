package com.dedalus.oas.environments;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "environments")
@XmlAccessorType(XmlAccessType.FIELD)
public class Environments {

    @XmlAttribute(name = "lastupdate")
    private String lastUpdate;

    @XmlElement(name = "environment_type")
    private List<EnvironmentType> environmentTypes;

    public List<EnvironmentType> getEnvironmentTypes() {
        return environmentTypes;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
