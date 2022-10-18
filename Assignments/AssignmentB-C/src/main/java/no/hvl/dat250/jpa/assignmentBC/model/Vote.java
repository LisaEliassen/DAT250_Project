package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteID;

    private String vote;
    /*
    @ManyToOne(targetEntity = FeedAppUser.class)
    @JoinColumn(name = "user_ID")
    private FeedAppUser feedAppUser;*/

    @ManyToOne(targetEntity = FeedAppUser.class)
    @JoinColumn(name = "user_ID")
    private Long userID;


    /*
    @ManyToOne(targetEntity = Poll.class)
    @JoinColumn(name = "poll_ID")
    private Poll poll;
    */
    @ManyToOne(targetEntity = Poll.class)
    @JoinColumn(name = "poll_ID")
    private Long pollID;
    /*
    @OneToOne(mappedBy = "vote")
    private IOTDevice iot;
     */

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public Long getUser() {
        return userID;
    }

    public void setUser(Long userID) {
        this.userID = userID;
    }

    public Long getPoll() {
        return pollID;
    }

    public void setPoll(Long pollID) {
        this.pollID = pollID;
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
