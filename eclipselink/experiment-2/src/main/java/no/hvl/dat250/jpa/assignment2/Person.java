package no.hvl.dat250.jpa.assignment2;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany()
    Set<Address> addresses;
    @OneToMany()
    Set<CreditCard> creditCards;

    public String getName() {
        return this.name;
    }

    public Collection<Address> getAddresses() {
        return this.addresses;
    }

    public Collection<CreditCard> getCreditCards() {
        return this.creditCards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void setCreditCards(CreditCard creditCard) {
        this.creditCards.add(creditCard);
    }
}
