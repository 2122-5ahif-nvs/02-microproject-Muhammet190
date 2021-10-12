package at.htl.fitnesstudio.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date registeredDate;
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
