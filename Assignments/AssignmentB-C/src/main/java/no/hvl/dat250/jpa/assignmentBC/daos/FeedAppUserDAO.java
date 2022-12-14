package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.FeedAppUser;
import no.hvl.dat250.jpa.assignmentBC.service.DatabaseService;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class FeedAppUserDAO {

    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManagerFactory factory;
    private DatabaseService dbService;

    public FeedAppUserDAO(DatabaseService dbService) {
        this.id_generator = new AtomicLong();
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.dbService = dbService;
    }

    public FeedAppUser create(FeedAppUser user) {
        /*
        if(user.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            user.setID(ID);
        }*/
        dbService.persist(user);
        return user;
    }

    public List<FeedAppUser> getAllUsers() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT u FROM FeedAppUser u");
        return query.getResultList();
    }

    public FeedAppUser getUserByID(Long id) {
        EntityManager em = factory.createEntityManager();
        FeedAppUser user = em.find(FeedAppUser.class, Long.valueOf(id));
        return user;
    }

    public FeedAppUser delete(Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        FeedAppUser user = getUserByID(id);
        if (user != null) {
            dbService.remove(user);
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

            dbService.merge(user);
            return updateUser;
        }
        else {
            return null;
        }
    }
}
