package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.Vote;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class VoteDAO {
    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManagerFactory factory;

    public VoteDAO() {
        this.id_generator = new AtomicLong();
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    private void executeInsideTransaction(EntityManager em, Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        }
        catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            em.close();
        }
    }

    public Vote create(Vote vote) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        if(vote.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            vote.setID(ID);
        }
        //executeInsideTransaction(em, em -> em.persist(vote));
        try {
            tx.begin();
            em.persist(vote);
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
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Vote vote = getVoteByID(id);
        if (vote != null) {
            //executeInsideTransaction(em, em -> em.remove(vote));
            try {
                tx.begin();
                em.remove(vote);
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
        return vote;
    }

    public Vote update(Vote vote, Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        if (getVoteByID(id) != null) {
            Vote updateVote = getVoteByID(id);
            updateVote.setVote(vote.getVote());
            //executeInsideTransaction(em, em -> em.merge(updateVote));
            try {
                tx.begin();
                em.merge(updateVote);
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

            return updateVote;
        }
        else {
            return null;
        }
    }
}
