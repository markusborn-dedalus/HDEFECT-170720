package com.dedalus.oas.environments;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class SoftwareInstallations {

    @XmlElement(name = "software")
    private List<Software> softwareList;

    public List<Software> getSoftwareList() {
        return softwareList;
    }
}
