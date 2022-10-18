package no.hvl.dat250.jpa.assignmentBC.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class FeedAppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String username;
    private String firstName;
    private String lastName;
    private boolean admin;

    private String password;

    /* @OneToMany(mappedBy = "feedAppUser")
     private Set<Poll> polls = new HashSet<>();*/
    @ElementCollection
    //@OneToMany(mappedBy = "feedAppUser")
    private List<Long> polls = new ArrayList<>();

    /*@OneToMany(mappedBy = "userID")
    private Set<Vote> votes = new HashSet<>();*/
    @ElementCollection
    //@OneToMany(mappedBy = "userID")
    private List<Long> votes = new ArrayList<>();

    public FeedAppUser() {
    }

    public FeedAppUser(Long id) {
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

    public List<Long> getPolls() {
        return polls;
    }

    public void addPoll(Long pollID) {
        this.polls.add(pollID);
    }

    public void setPolls(List<Long> polls) {
        this.polls = polls;
    }

    public List<Long> getVotes() {
        return votes;
    }

    public void addVote(Long voteID) {
        this.votes.add(voteID);
    }

    public void setVotes(List<Long> votes) {
        this.votes = votes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
