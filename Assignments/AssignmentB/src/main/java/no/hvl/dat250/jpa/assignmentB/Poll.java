package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollID;
    private String pollName;

    private String category;

    private String description;

    private String pollResult;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_ID")
    private User user;

    @OneToMany(mappedBy = "poll")
    private Set<Vote> votes = new HashSet<>();

    public Long getPollID() {
        return pollID;
    }

    public void setPollID(Long pollID) {
        this.pollID = pollID;
    }

    public String getPollName() {
        return this.pollName;
    }

    public void setPollName(String newName) {
        this.pollName = newName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPollResult() {
        return pollResult;
    }

    public void setPollResult(String pollResult) {
        this.pollResult = pollResult;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVote(Vote vote) {
        this.votes.add(vote);
    }

}
