package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class FeedAppUser2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String username;
    private String firstName;
    private String lastName;
    private boolean admin;

    private String password;

    @OneToMany(mappedBy = "feedappuser", cascade = CascadeType.ALL)
    private List<Poll2> polls = new ArrayList<>();

    @OneToMany(mappedBy = "feedappuser", cascade = CascadeType.ALL)
    private List<Vote2> votes = new ArrayList<>();

    public FeedAppUser2() {
    }

    public FeedAppUser2(Long id) {
        this.setID(id);
    }

    public Long getID() {
        return userID;
    }

    public void setID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String newName) {
        this.firstName = newName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Poll2> getPolls() {
        return polls;
    }

    public FeedAppUser2 addPoll(Poll2 poll) {
        this.polls.add(poll);
        return this;
    }

    public void setPolls(List<Poll2> polls) {
        this.polls = polls;
    }

    public List<Vote2> getVotes() {
        return votes;
    }

    public void addVote(Vote2 vote) {
        this.votes.add(vote);
    }

    public void setVotes(List<Vote2> votes) {
        this.votes = votes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
