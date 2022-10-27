package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;

@Entity
public class Vote2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteID;

    private String vote;

    @ManyToOne(targetEntity = FeedAppUser2.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "feedappuser")
    private FeedAppUser2 feedappuser;

    @ManyToOne(targetEntity = Poll2.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pollID")
    private Poll2 poll;

    /*
    @OneToOne(mappedBy = "vote")
    private IOTDevice iot; */

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public FeedAppUser2 getUser() {
        return feedappuser;
    }

    public void setUser(FeedAppUser2 userID) {
        this.feedappuser = feedappuser;
    }

    public Poll2 getPoll() {
        return poll;
    }

    public void setPoll(Poll2 poll) {
        this.poll = poll;
    }

    public Long getID() {
        return voteID;
    }

    public void setID(Long voteID) {
        this.voteID = voteID;
    }

    /*
    public IOTDevice getIot() {
        return iot;
    }

    public void setIot(IOTDevice iot) {
        this.iot = iot;
    }*/
}
