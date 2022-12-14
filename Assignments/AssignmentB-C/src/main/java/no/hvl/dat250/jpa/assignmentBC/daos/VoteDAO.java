package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.Vote;
import no.hvl.dat250.jpa.assignmentBC.service.DatabaseService;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class VoteDAO {
    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManagerFactory factory;
    private DatabaseService dbService;

    public VoteDAO(DatabaseService dbService) {
        this.id_generator = new AtomicLong();
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.dbService = dbService;
    }

    public Vote create(Vote vote) {
        /*if(vote.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            vote.setID(ID);
        }*/

        dbService.persist(vote);
        return vote;
    }

    public List<Vote> getAllVotes() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT v FROM Vote v");
        return query.getResultList();
    }

    public Vote getVoteByID(Long id) {
        EntityManager em = factory.createEntityManager();
        Vote vote = em.find(Vote.class, Long.valueOf(id));
        return vote;
    }

    public Vote delete(Long id) {
        Vote vote = getVoteByID(id);
        if (vote != null) {
            dbService.remove(vote);
        }
        return vote;
    }

    public Vote update(Vote vote, Long id) {
        if (getVoteByID(id) != null) {
            Vote updateVote = getVoteByID(id);
            updateVote.setVote(vote.getVote());
            dbService.merge(updateVote);
            return updateVote;
        }
        else {
            return null;
        }
    }
}
