package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.IOTDevice;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class IOTDeviceDAO {

    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManager em;
    public IOTDeviceDAO() {
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

    public IOTDevice create(IOTDevice iot) {
        if(iot.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            iot.setID(ID);
        }
        executeInsideTransaction(em -> em.persist(iot));
        return iot;
    }

    public List<IOTDevice> getAllDevices() {
        Query query = em.createQuery("SELECT d FROM IOTDevice d");
        return query.getResultList();
    }

    public IOTDevice getDeviceByID(Long id) {
        IOTDevice iot = em.find(IOTDevice.class, Long.valueOf(id));
        return iot;
    }

    public IOTDevice delete(Long id) {
        IOTDevice iot = getDeviceByID(id);
        if (iot != null) {
            executeInsideTransaction(em -> em.remove(iot));
        }
        return iot;    }

    public IOTDevice update(IOTDevice device, Long id) {
        if (getDeviceByID(id) != null) {
            IOTDevice updateDevice = getDeviceByID(id);
            updateDevice.setPoll(device.getPoll());
            executeInsideTransaction(em -> em.merge(updateDevice));
            return updateDevice;
        }
        else {
            return null;
        }
    }
}
