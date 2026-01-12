package com.dedalus.oas.environments;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EnvironmentType {

    @XmlAttribute(name = "type")
    private String type;

    @XmlElement(name = "environment")
    private List<Environment> environments;

    public List<Environment> getEnvironments() {
        return environments;
    }

    public String getType() {
        return type;
    }
}
