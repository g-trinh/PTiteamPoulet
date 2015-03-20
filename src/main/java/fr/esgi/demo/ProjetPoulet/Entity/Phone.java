package fr.esgi.demo.ProjetPoulet.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Guillaume on 20/03/2015.
 */
@Entity(name = "phone")
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "serialnumber")
    private String serialNumber;

    @Column(name="telnumber")
    private String number;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="stolen")
    private boolean stolen;

    public Phone(){}

    private Phone(Builder builder) {
        setId(builder.id);
        setSerialNumber(builder.serialNumber);
        setNumber(builder.number);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setStolen(builder.stolen);
    }

    public static Builder newUser(){
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isStolen() {
        return stolen;
    }

    public void setStolen(boolean stolen) {
        this.stolen = stolen;
    }

    public static final class Builder {
        private Long id;
        private String serialNumber;
        private String number;
        private String firstName;
        private String lastName;
        private boolean stolen;

        public Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withStolen(boolean stolen) {
            this.stolen = stolen;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }
    }
}
