package at.htl.fitnesstudio.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;

@Entity
@Schema(description = "Employees of my fitnessstudio")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonbProperty("name")
    @Schema(required = true)
    private String name;
    @JsonbProperty("salary")
    @Schema(required = true)
    private int salary;


    public Employee() {
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name +" "+ salary;
    }
}
