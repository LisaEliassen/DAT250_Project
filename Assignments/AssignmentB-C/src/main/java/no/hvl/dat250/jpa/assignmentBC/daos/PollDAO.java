package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.Poll;
import no.hvl.dat250.jpa.assignmentBC.model.Vote;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class PollDAO {
    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManager em;
    EntityTransaction tx;


    public PollDAO() {
        this.id_generator = new AtomicLong();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        if (em.isOpen()) {
            tx = em.getTransaction();
        }
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        // Todo: Find a way to use EntityTransaction.commit(), as this is needed to put data into database.
        //em.joinTransaction();
        try {
            //tx.begin();
            action.accept(em);
            /*if (tx.isActive()) {
                tx.commit();
            }*/
        }
        catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public Poll create(Poll poll) {
        if(poll.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            poll.setID(ID);
        }
        executeInsideTransaction(em -> em.persist(poll));
        return poll;
    }

    public void addVote(Long voteID, Long pollID) {
        if (getPollByID(pollID) != null) {
            this.getPollByID(pollID).addVote(voteID);
            //vote.getPoll().addVote(vote);
        }
    }

    public List<Poll> getAllPolls() {
        Query query = em.createQuery("SELECT p FROM Poll p");
        return query.getResultList();
    }

    public Poll getPollByID(Long id) {
        Poll poll = em.find(Poll.class, Long.valueOf(id));
        return poll;
    }

    public Poll delete(Long id) {
        Poll poll = getPollByID(id);
        if (poll != null) {
            executeInsideTransaction(em -> em.remove(poll));
        }
        return poll;
    }

    public Poll update(Poll poll, Long id) {
        if (getPollByID(id) != null) {
            Poll updatePoll = getPollByID(id);
            updatePoll.setName(poll.getName());
            updatePoll.setDescription(poll.getDescription());
            updatePoll.setCategory(poll.getCategory());
            updatePoll.setResult(poll.getResult());
            updatePoll.setVotes(poll.getAllVotes());
            executeInsideTransaction(em -> em.merge(updatePoll));
            return updatePoll;
        }
        else {
            return null;
        }
    }
}
