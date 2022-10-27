package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.Poll;
import no.hvl.dat250.jpa.assignmentBC.service.DatabaseService;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class PollDAO {
    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    //private EntityManager em;
    private EntityManagerFactory factory;
    private DatabaseService dbService;

    public PollDAO(DatabaseService dbService) {
        this.id_generator = new AtomicLong();
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.dbService = dbService;
    }

    public Poll create(Poll poll) {
        if(poll.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            poll.setID(ID);
        }
        dbService.persist(poll);
        System.out.println(poll.getID());
        return poll;
    }

    public void addVote(Long voteID, Long pollID) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        if (getPollByID(pollID) != null) {
            //this.getPollByID(pollID).addVote(voteID);
            //vote.getPoll().addVote(vote);
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
        Poll poll = getPollByID(id);
        if (poll != null) {
            dbService.remove(poll);
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
