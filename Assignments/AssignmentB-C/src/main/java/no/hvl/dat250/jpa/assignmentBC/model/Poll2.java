package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Poll2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollID;
    private String pollName;
    private String category;
    private String description;
    private String pollResult;

    @ManyToOne(targetEntity = FeedAppUser2.class, cascade = CascadeType.MERGE)
    //@JoinColumn(name = "feedappuser_id")
    private FeedAppUser2 feedappuser;

    @OneToMany(mappedBy = "poll", targetEntity = Vote2.class, cascade = CascadeType.ALL)
    private List<Vote2> votes = new ArrayList<>();

    @OneToMany(mappedBy = "poll", targetEntity = IOTDevice2.class, cascade = CascadeType.ALL)
    private List<IOTDevice2> iots = new ArrayList<>();

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

    public FeedAppUser2 getUser() {
        return this.feedappuser;
    }

    public void setUser(FeedAppUser2 user) {
        this.feedappuser = user;
    }

    public void setIoT(List<IOTDevice2> iotIDs) {
        this.iots.addAll(iots);
    }

    public void addIoT(IOTDevice2 iot) {
        this.iots.add(iot);
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

    public List<Vote2> getVotes() {
        return votes;
    }

    public void addVote(Vote2 vote) {
        this.votes.add(vote);
        //updateResult();
    }

    public List<Vote2> getAllVotes() {
        return this.votes;
    }

    public void setVotes(List<Vote2> votes) {
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
