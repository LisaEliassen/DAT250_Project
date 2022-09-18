package no.hvl.dat250.jpa.assignmentB;

import java.util.ArrayList;
import java.util.List;

public class VoteDAO {
    private List<Vote> votes;

    public VoteDAO() {
        votes = new ArrayList<>();
    }

    public List<Vote> getAllVotes() {
        return this.votes;
    }

    public Vote getVoteByID(Long id) {
        for (Vote vote : getAllVotes()) {
            if (vote.getVoteID().equals(id)) {
                return vote;
            }
        }
        return null;
    }

    public void save(Vote vote) {
        getAllVotes().add(vote);
    }

    public void delete(Vote vote) {
        getAllVotes().remove(vote);
    }

    public void update(Vote vote, String[] params) {
        vote.setVote(params[0]);
    }
}
