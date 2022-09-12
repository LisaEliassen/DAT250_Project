package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;
import java.util.*;

@Entity
public class VotingUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "votes")
    List<Vote> votes = new ArrayList<>();

    public Collection<Vote> getVotes() {
        return this.votes;
    }
}
