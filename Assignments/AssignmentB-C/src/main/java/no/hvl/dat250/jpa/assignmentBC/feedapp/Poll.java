package no.hvl.dat250.jpa.assignmentBC.feedapp;

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

    @ManyToOne(targetEntity = FeedAppUser.class)
    @JoinColumn(name = "user_ID")
    private FeedAppUser feedAppUser;

    @OneToMany(mappedBy = "poll")
    private Set<Vote> votes = new HashSet<>();

    @OneToOne(mappedBy = "poll")
    private IOTDevice iot;

    public Long getID() {
        return pollID;
    }

    public void setID(Long pollID) {
        this.pollID = pollID;
    }

    public String getName() {
        return this.pollName;
    }

    public void setName(String newName) {
        this.pollName = newName;
    }

    public FeedAppUser getUser() {
        return feedAppUser;
    }

    public void setUser(FeedAppUser feedAppUser) {
        this.feedAppUser = feedAppUser;
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

    public String getResult() {
        return pollResult;
    }

    public void setResult(String pollResult) {
        this.pollResult = pollResult;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void addVote(Vote vote) {
        this.votes.add(vote);
        updateResult();
    }

    public Set<Vote> getAllVotes() {
         return this.votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    private void updateResult() {
        int yes = 0;
        int no = 0;
        for (Vote vote : this.votes) {
            // Todo: update result of pollResult. We need to potentially use a HashMap to make things efficient.
            if (vote.getVote() == "yes") {
                yes++;
            }
            else if (vote.getVote() == "no") {
                no++;
            }
        }
        this.pollResult = String.format("{yes: \"%s\", no: \"%s\"}", yes, no);
    }

}
