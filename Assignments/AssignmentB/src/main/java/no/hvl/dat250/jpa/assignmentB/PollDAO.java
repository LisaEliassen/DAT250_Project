package no.hvl.dat250.jpa.assignmentB;

import java.util.ArrayList;
import java.util.List;

public class PollDAO {
    private List<Poll> polls;

    public PollDAO() {
        polls = new ArrayList<>();
    }

    public List<Poll> getAllPolls() {
        return this.polls;
    }

    public Poll getPollByID(Long id) {
        for (Poll poll : getAllPolls()) {
            if (poll.getPollID().equals(id)) {
                return poll;
            }
        }
        return null;
    }

    public void save(Poll poll) {
        getAllPolls().add(poll);
    }

    public void delete(Poll poll) {
        getAllPolls().remove(poll);
    }

    public void update(Poll poll, String[] params) {
        poll.setPollName(params[0]);
        poll.setCategory(params[1]);
        poll.setDescription(params[2]);
        poll.setPollResult(params[3]);
    }
}
