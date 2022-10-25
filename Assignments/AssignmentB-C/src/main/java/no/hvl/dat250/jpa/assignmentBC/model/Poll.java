package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Long userID;

    @ElementCollection
    private List<Long> votes = new ArrayList<>();

    @OneToOne(targetEntity = IOTDevice.class)
    private Long iotID;

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

    public Long getUser() {
        return this.userID;
    }

    public void setUser(Long userID) {
        this.userID = userID;
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

    public List<Long> getVotes() {
        return votes;
    }

    public void addVote(Long voteID) {
        this.votes.add(voteID);
        //updateResult();
    }

    public List<Long> getAllVotes() {
         return this.votes;
    }

    public void setVotes(List<Long> votes) {
        this.votes = votes;
    }

    private void updateResult() {
        int yes = 0;
        int no = 0;
        /*
        for (Long voteID : this.votes) {
            // Todo: update result of pollResult. We need to potentially use a HashMap to make things efficient.
            if (vote.getVote() == "yes") {
                yes++;
            }
            else if (vote.getVote() == "no") {
                no++;
            }
        }*/
        this.pollResult = String.format("yes: \"%s\", no: \"%s\"", yes, no);
    }

}
