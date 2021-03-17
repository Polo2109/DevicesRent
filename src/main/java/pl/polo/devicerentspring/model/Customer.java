package pl.polo.devicerentspring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final Long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column(name= "surname")
    private String surname;

    @Column(name = "pesel", length = 11)
    private String pesel;

    @Column(name = "idCard", length = 10)
    private String idCard;

    @ManyToMany(mappedBy = "customers")
    private List<Device> devices = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long id, String name, String surname, String pesel, String idCard, List<Device> devices) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.idCard = idCard;
        this.devices = devices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(pesel, customer.pesel) &&
                Objects.equals(idCard, customer.idCard) &&
                Objects.equals(devices, customer.devices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, pesel, idCard, devices);
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imię='" + name + '\'' +
                ", nazwisko='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                ", numer dowodu osobistego='" + idCard + '\'' +
                '}';
    }
}
