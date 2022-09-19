package no.hvl.dat250.jpa.assignmentB;

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

    public Long getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Long deviceID) {
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
