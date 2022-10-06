package no.hvl.dat250.jpa.assignmentB.feedapp;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "feedAppUser")
    private Set<Poll> polls = new HashSet<>();

    @OneToMany(mappedBy = "feedAppUser")
    private Set<Vote> votes = new HashSet<>();

    public FeedAppUser() {
    }
    public FeedAppUser(Long id) {
        this.setUserID(id);
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
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

    public Set<Poll> getPoll() {
        return polls;
    }

    public void setPoll(Poll poll) {
        this.polls.add(poll);
    }

    public Set<Vote> getVote() {
        return votes;
    }

    public void setVote(Vote vote) {
        this.votes.add(vote);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
