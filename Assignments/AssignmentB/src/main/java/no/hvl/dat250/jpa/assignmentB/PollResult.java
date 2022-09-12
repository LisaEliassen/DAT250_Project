package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PollResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer number;

    @ManyToMany(mappedBy = "pollResults")
    private Set<VotingUser> owners = new HashSet<>();

    public String getStreet() {
        return this.street;
    }

    public Integer getNumber() {
        return this.number;
    }
    public Collection<VotingUser> getOwners() {
        return this.owners;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void addOwner(VotingUser votingUser) {
        owners.add(votingUser);
    }
}
