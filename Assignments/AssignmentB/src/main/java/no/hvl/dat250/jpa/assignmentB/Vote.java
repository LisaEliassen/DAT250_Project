package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String vote;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_ID")
    private User user;

    @ManyToOne(targetEntity = Poll.class)
    @JoinColumn(name = "poll_ID")
    private Poll poll;

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

}
