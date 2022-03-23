package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "department")
public class Department {
    @XmlAttribute
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + name + '\''
                + '}';
    }
}
