package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollID;
    private String pollName;

    public Long getPollID() {
        return pollID;
    }

    public void setPollID(Long pollID) {
        this.pollID = pollID;
    }

    public String getPollName() {
        return this.pollName;
    }

    public void setPollName(String newName) {
        this.pollName = newName;
    }

}
