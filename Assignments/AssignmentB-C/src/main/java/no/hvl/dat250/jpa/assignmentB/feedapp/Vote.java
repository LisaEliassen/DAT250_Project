package no.hvl.dat250.jpa.assignmentB.feedapp;

import no.hvl.dat250.jpa.assignmentB.feedapp.FeedAppUser;
import no.hvl.dat250.jpa.assignmentB.feedapp.IOTDevice;
import no.hvl.dat250.jpa.assignmentB.feedapp.Poll;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteID;

    private String vote;

    @ManyToOne(targetEntity = FeedAppUser.class)
    @JoinColumn(name = "user_ID")
    private FeedAppUser feedAppUser;

    @ManyToOne(targetEntity = Poll.class)
    @JoinColumn(name = "poll_ID")
    private Poll poll;

    @OneToOne(mappedBy = "vote")
    private IOTDevice iot;

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public FeedAppUser getUser() {
        return feedAppUser;
    }

    public void setUser(FeedAppUser feedAppUser) {
        this.feedAppUser = feedAppUser;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Long getVoteID() {
        return voteID;
    }

    public void setVoteID(Long voteID) {
        this.voteID = voteID;
    }

    public IOTDevice getIot() {
        return iot;
    }

    public void setIot(IOTDevice iot) {
        this.iot = iot;
    }
}
