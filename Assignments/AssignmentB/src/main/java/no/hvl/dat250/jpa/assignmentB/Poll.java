package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "poll")
    private Set<Vote> ownedCards = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public Set<Vote> getOwnedCards() {
        return this.ownedCards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addOwnedCard(Vote card) {
        getOwnedCards().add(card);
    }
}
