package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private boolean status;
    @XmlAttribute
    private String name;
    private int age;
    private Department department;
    private String[] duties;

    public Employee() {
    }

    public Employee(boolean status, String name, int age, Department department, String... duties) {
        this.status = status;
        this.name = name;
        this.age = age;
        this.department = department;
        this.duties = duties;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + status
                + "name=" + name
                + ", age=" + age
                + ", contact=" + department
                + ", statuses=" + Arrays.toString(duties)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Employee employee = new Employee(
                false, "Addy", 30, new Department("DRT"), "support", "consult");
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employee, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
