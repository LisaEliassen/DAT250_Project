package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;

@Entity
public class IOTDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceID;

    @OneToOne
    private Poll poll;

    @OneToOne
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
