package com.dedalus.oas.environments;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Software {
    public String getName() {
        return name;
    }

    public String getSwTypeName() {
        return swTypeName;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public boolean isOas() {
        return this.name.startsWith("OAS");
    }

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "sw_type_name")
    private String swTypeName;

    private String server;

    private int port;
}
