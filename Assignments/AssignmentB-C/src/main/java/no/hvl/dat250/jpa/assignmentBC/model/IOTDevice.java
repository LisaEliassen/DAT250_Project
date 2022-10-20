package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;
import javax.print.attribute.standard.JobImpressions;
import java.util.ArrayList;
import java.util.List;

@Entity
public class IOTDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceID;

    @OneToOne(targetEntity = Poll.class)
    private Long pollID;

    /*
    @OneToOne(mappedBy="voteID")
    private Vote vote;
     */
    @ElementCollection
    private List<Long> votes = new ArrayList<>();

    public Long getID() {
        return deviceID;
    }

    public void setID(Long deviceID) {
        this.deviceID = deviceID;
    }

    public Long getPoll() {
        return pollID;
    }

    public void setPoll(Long pollID) {
        this.pollID = pollID;
    }

    public List<Long> getVotes() {
        return this.votes;
    }

    public void setVote(List<Long> votes) {
        this.votes = votes;
    }

    public void addVote(Long voteID) {
        this.votes.add(voteID);
    }
}
