package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;
import javax.print.attribute.standard.JobImpressions;

@Entity
public class IOTDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceID;

    @OneToOne(mappedBy="pollID")
    private Poll poll;

    @OneToOne(mappedBy="voteID")
    private Vote vote;

    public Long getID() {
        return deviceID;
    }

    public void setID(Long deviceID) {
        this.deviceID = deviceID;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}
