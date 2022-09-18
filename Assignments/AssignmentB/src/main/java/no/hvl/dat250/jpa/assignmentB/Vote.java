package no.hvl.dat250.jpa.assignmentB;

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

}
