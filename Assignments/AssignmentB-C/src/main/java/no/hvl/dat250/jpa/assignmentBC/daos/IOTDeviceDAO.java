package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.IOTDevice;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class IOTDeviceDAO {

    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManagerFactory factory;
    public IOTDeviceDAO() {
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

    public IOTDevice create(IOTDevice iot) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(iot.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            iot.setID(ID);
        }
        //executeInsideTransaction(em, em -> em.persist(iot));
        try {
            tx.begin();
            em.persist(iot);
            tx.commit();
        }
        catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            em.close();
        }
        return iot;
    }

    public List<IOTDevice> getAllDevices() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT d FROM IOTDevice d");
        return query.getResultList();
    }

    public IOTDevice getDeviceByID(Long id) {
        EntityManager em = factory.createEntityManager();
        IOTDevice iot = em.find(IOTDevice.class, Long.valueOf(id));
        return iot;
    }

    public IOTDevice delete(Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        IOTDevice iot = getDeviceByID(id);
        if (iot != null) {
            //executeInsideTransaction(em, em -> em.remove(iot));
            try {
                tx.begin();
                em.remove(iot);
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
        return iot;    }

    public IOTDevice update(IOTDevice device, Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        if (getDeviceByID(id) != null) {
            IOTDevice updateDevice = getDeviceByID(id);
            updateDevice.setPoll(device.getPoll());
            //executeInsideTransaction(em, em -> em.merge(updateDevice));
            try {
                tx.begin();
                em.merge(updateDevice);
                tx.commit();
            }
            catch (Throwable e) {
                e.printStackTrace();
                tx.rollback();
            }
            finally {
                em.close();
            }

            return updateDevice;
        }
        else {
            return null;
        }
    }
}
