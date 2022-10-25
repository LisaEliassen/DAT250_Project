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
    //private EntityManager em;
    private EntityManagerFactory factory;

    public PollDAO() {
        this.id_generator = new AtomicLong();
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    private void executeInsideTransaction(EntityManager em, Consumer<EntityManager> action) {
        // Todo: Find a way to use EntityTransaction.commit(), as this is needed to put data into database.
        //em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        }
        catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        finally {
            em.close();
        }
    }

    public Poll create(Poll poll) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        if(poll.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            poll.setID(ID);
        }

        try {
            tx.begin();
            em.persist(poll);
            tx.commit();
        }
        catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        finally {
            em.close();
        }
        //executeInsideTransaction(em, em -> em.persist(poll));
        return poll;
    }

    public void addVote(Long voteID, Long pollID) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        if (getPollByID(pollID) != null) {
            this.getPollByID(pollID).addVote(voteID);
            //vote.getPoll().addVote(vote);

            try {
                tx.begin();
                em.merge(this.getPollByID(pollID));
                tx.commit();
            }
            catch (Throwable e) {
                e.printStackTrace();
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
            finally {
                em.close();
            }
        }
    }

    public List<Poll> getAllPolls() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Poll p");
        List<Poll> allPolls = query.getResultList();
        return allPolls;
    }

    public Poll getPollByID(Long id) {
        EntityManager em = factory.createEntityManager();
        Poll poll = em.find(Poll.class, Long.valueOf(id));
        return poll;
    }

    public Poll delete(Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Poll poll = getPollByID(id);
        if (poll != null) {
            //executeInsideTransaction(em, em -> em.remove(poll));
            try {
                tx.begin();
                em.remove(poll);
                tx.commit();
            }
            catch (Throwable e) {
                e.printStackTrace();
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
            finally {
                em.close();
            }
        }
        return poll;
    }

    public Poll update(Poll poll, Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        if (getPollByID(id) != null) {
            Poll updatePoll = getPollByID(id);
            updatePoll.setName(poll.getName());
            updatePoll.setDescription(poll.getDescription());
            updatePoll.setCategory(poll.getCategory());
            updatePoll.setResult(poll.getResult());
            updatePoll.setVotes(poll.getAllVotes());

            //executeInsideTransaction(em, em -> em.merge(updatePoll));

            try {
                tx.begin();
                em.merge(updatePoll);
                tx.commit();
            }
            catch (Throwable e) {
                e.printStackTrace();
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
            finally {
                em.close();
            }

            return updatePoll;
        }
        else {
            return null;
        }
    }
}
