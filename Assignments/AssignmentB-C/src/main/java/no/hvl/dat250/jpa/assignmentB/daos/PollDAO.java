package no.hvl.dat250.jpa.assignmentB.daos;

import no.hvl.dat250.jpa.assignmentB.feedapp.Poll;
import no.hvl.dat250.jpa.assignmentB.feedapp.Vote;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class PollDAO {
    private Map<Long, Poll> polls;
    private final AtomicLong id_generator;

    public PollDAO() {
        polls = new HashMap();
        this.id_generator = new AtomicLong();
    }

    public Poll create(Poll poll) {
        if(poll.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            poll.setID(ID);
        }

        this.polls.put(poll.getID(), poll);
        return poll;
    }

    public void addVote(Vote vote) {
        if (vote.getPoll().getID() != null) {
            vote.getPoll().setVote(vote);
        }
    }

    public Collection<Poll> getAllPolls() {
        return this.polls.values();
    }

    public Poll getPollByID(Long id) {
        return this.polls.get(id);
    }

    public void save(Poll poll) {
        getAllPolls().add(poll);
    }

    public Poll delete(Long id) {
        Poll poll = getPollByID(id);
        if (poll != null && this.polls.containsKey(id)) {
            this.polls.remove(id);
        }
        return poll;
    }

    public Poll update(Poll poll, Long id) {
        if (getPollByID(id) != null && this.polls.containsKey(id)) {
            Poll updatePoll = getPollByID(id);
            updatePoll.setDescription(poll.getDescription());
            return updatePoll;
        }
        else {
            return null;
        }
    }
}
