package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.Vote;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class VoteDAO {
    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManager em;

    public VoteDAO() {
        this.id_generator = new AtomicLong();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    public Vote create(Vote vote) {
        if(vote.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            vote.setID(ID);
        }
        executeInsideTransaction(em -> em.persist(vote));
        return vote;
    }

    public List<Vote> getAllVotes() {
        Query query = em.createQuery("SELECT v FROM Vote v");
        return query.getResultList();
    }

    public Vote getVoteByID(Long id) {
        Vote vote = em.find(Vote.class, Long.valueOf(id));
        return vote;
    }

    public Vote delete(Long id) {
        Vote vote = getVoteByID(id);
        if (vote != null) {
            executeInsideTransaction(em -> em.remove(vote));
        }
        return vote;
    }

    public Vote update(Vote vote, Long id) {
        if (getVoteByID(id) != null) {
            Vote updateVote = getVoteByID(id);
            updateVote.setVote(vote.getVote());
            executeInsideTransaction(em -> em.merge(updateVote));
            return updateVote;
        }
        else {
            return null;
        }
    }
}
