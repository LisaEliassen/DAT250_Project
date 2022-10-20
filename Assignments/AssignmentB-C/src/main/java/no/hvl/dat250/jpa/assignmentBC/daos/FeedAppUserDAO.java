package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.FeedAppUser;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class FeedAppUserDAO {

    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManager em;

    public FeedAppUserDAO() {
        this.id_generator = new AtomicLong();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        // Todo: Find a way to use EntityTransaction.commit(), as this is needed to put data into database.
        EntityTransaction tx = em.getTransaction();
        try {
            //tx.begin();
            action.accept(em);
            //tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    public FeedAppUser create(FeedAppUser user) {
        if(user.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            user.setID(ID);
        }
        executeInsideTransaction(em -> em.persist(user));
        return user;
    }

    public List<FeedAppUser> getAllUsers() {
        Query query = em.createQuery("SELECT u FROM FeedAppUser u");
        return query.getResultList();
    }

    public FeedAppUser getUserByID(Long id) {
        FeedAppUser user = em.find(FeedAppUser.class, Long.valueOf(id));
        return user;
    }

    public FeedAppUser delete(Long id) {
        FeedAppUser user = getUserByID(id);
        if (user != null) {
            executeInsideTransaction(em -> em.remove(user));
        }
        return user;
    }

    public FeedAppUser update(FeedAppUser user, Long id) {
        if (getUserByID(id) != null) {
            FeedAppUser updateUser = getUserByID(id);
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setUsername(user.getUsername());
            updateUser.setPolls(user.getPolls());
            updateUser.setVotes(user.getVotes());
            updateUser.setPassword(user.getPassword());

            executeInsideTransaction(em -> em.merge(updateUser));
            return updateUser;
        }
        else {
            return null;
        }
    }
}
