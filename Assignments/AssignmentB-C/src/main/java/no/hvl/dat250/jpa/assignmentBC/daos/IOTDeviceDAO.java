package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.model.IOTDevice;
import no.hvl.dat250.jpa.assignmentBC.service.DatabaseService;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class IOTDeviceDAO {

    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManagerFactory factory;
    private DatabaseService dbService;
    public IOTDeviceDAO(DatabaseService dbService) {
        this.id_generator = new AtomicLong();
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.dbService = dbService;
    }

    public IOTDevice create(IOTDevice iot) {
        /*
        if(iot.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            iot.setID(ID);
        }
         */
        dbService.persist(iot);
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
           dbService.remove(iot);
        }
        return iot;    }

    public IOTDevice update(IOTDevice device, Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        if (getDeviceByID(id) != null) {
            IOTDevice updateDevice = getDeviceByID(id);
            updateDevice.setPoll(device.getPoll());
            dbService.merge(updateDevice);

            return updateDevice;
        }
        else {
            return null;
        }
    }
}
