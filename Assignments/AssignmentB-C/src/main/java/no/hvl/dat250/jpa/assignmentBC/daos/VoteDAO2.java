package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.Vote2;
import no.hvl.dat250.jpa.assignmentBC.service.DatabaseService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class VoteDAO2 {
    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManagerFactory factory;
    private DatabaseService dbService;

    public VoteDAO2(DatabaseService dbService) {
        this.id_generator = new AtomicLong();
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.dbService = dbService;
    }

    public Vote2 create(Vote2 vote) {
        /*if(vote.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            vote.setID(ID);
        }*/

        dbService.persist(vote);
        return vote;
    }

    public List<Vote2> getAllVotes() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT v FROM Vote2 v");
        return query.getResultList();
    }

    public Vote2 getVoteByID(Long id) {
        EntityManager em = factory.createEntityManager();
        Vote2 vote = em.find(Vote2.class, Long.valueOf(id));
        return vote;
    }

    public Vote2 delete(Long id) {
        Vote2 vote = getVoteByID(id);
        if (vote != null) {
            dbService.remove(vote);
        }
        return vote;
    }

    public Vote2 update(Vote2 vote, Long id) {
        if (getVoteByID(id) != null) {
            Vote2 updateVote = getVoteByID(id);
            updateVote.setVote(vote.getVote());
            dbService.merge(updateVote);
            return updateVote;
        }
        else {
            return null;
        }
    }
}
