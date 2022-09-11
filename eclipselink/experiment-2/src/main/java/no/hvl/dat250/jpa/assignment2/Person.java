package no.hvl.dat250.jpa.assignment2;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "addresses_fk")
    Set<Address> addresses = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "creditcards_fk")
    Set<CreditCard> creditCards = new HashSet<>();;

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

    public void addCreditCard(CreditCard creditCard) {
        this.creditCards.add(creditCard);
    }
}
