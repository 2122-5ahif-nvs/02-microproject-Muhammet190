package at.htl.fitnesstudio.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.Date;

@Entity
@Schema(description = "Customer of my fitnessstudio")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonbProperty("registered_date")
    private Date registeredDate;
    @Schema(required = true)
    private String name;


    public Customer() {
    }

    public Customer(Date registeredDate, String name) {
        this.registeredDate = registeredDate;
        this.name = name;
    }


    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
