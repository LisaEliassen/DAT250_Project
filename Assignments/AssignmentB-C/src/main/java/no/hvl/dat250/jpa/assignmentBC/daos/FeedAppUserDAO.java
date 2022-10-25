package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.FeedAppUser;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class FeedAppUserDAO {

    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManagerFactory factory;

    public FeedAppUserDAO() {
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

    public FeedAppUser create(FeedAppUser user) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        if(user.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            user.setID(ID);
        }
        //executeInsideTransaction(em, em -> em.persist(user));
        try {
            tx.begin();
            em.persist(user);
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
            //executeInsideTransaction(em, em -> em.remove(user));
            try {
                tx.begin();
                em.remove(user);
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
        return user;
    }

    public FeedAppUser update(FeedAppUser user, Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        if (getUserByID(id) != null) {
            FeedAppUser updateUser = getUserByID(id);
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setUsername(user.getUsername());
            updateUser.setPolls(user.getPolls());
            updateUser.setVotes(user.getVotes());
            updateUser.setPassword(user.getPassword());

            //executeInsideTransaction(em, em -> em.merge(updateUser));
            try {
                tx.begin();
                em.merge(updateUser);
                tx.commit();
            }
            catch (Throwable e) {
                e.printStackTrace();
                tx.rollback();
            }
            finally {
                em.close();
            }
            return updateUser;
        }
        else {
            return null;
        }
    }
}
