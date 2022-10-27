package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;
import javax.print.attribute.standard.JobImpressions;
import java.util.ArrayList;
import java.util.List;

@Entity
public class IOTDevice2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceID;

    @ManyToOne(targetEntity = Poll2.class)
    @JoinColumn(name = "pollID")
    private Poll2 poll;

    /*
    @OneToOne(mappedBy="voteID")
    private Vote vote;
     */

    public Long getID() {
        return deviceID;
    }

    public void setID(Long deviceID) {
        this.deviceID = deviceID;
    }

    public Poll2 getPoll() {
        return this.poll;
    }

    public void setPoll(Poll2 poll) {
        this.poll = poll;
    }
    /*
    public List<Vote2> getVotes() {
        return this.votes;
    }

    public void setVote(List<Vote2> votes) {
        this.votes = votes;
    }

    public void addVote(Vote2 vote) {
        this.votes.add(vote);
    }*/
}
