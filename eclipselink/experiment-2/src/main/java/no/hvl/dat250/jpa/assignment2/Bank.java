package no.hvl.dat250.jpa.assignment2;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany()
    private Set<CreditCard> ownedCards;

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public Set<CreditCard> getOwnedCards() {
        return this.ownedCards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addOwnedCard(CreditCard card) {
        getOwnedCards().add(card);
    }
}
