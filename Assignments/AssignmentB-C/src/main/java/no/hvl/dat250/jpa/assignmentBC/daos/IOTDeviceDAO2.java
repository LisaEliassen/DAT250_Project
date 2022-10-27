package no.hvl.dat250.jpa.assignmentBC.daos;

import no.hvl.dat250.jpa.assignmentBC.service.DatabaseService;
import no.hvl.dat250.jpa.assignmentBC.model.IOTDevice2;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class IOTDeviceDAO2 {

    private final AtomicLong id_generator;
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";
    private EntityManagerFactory factory;
    private DatabaseService dbService;
    public IOTDeviceDAO2(DatabaseService dbService) {
        this.id_generator = new AtomicLong();
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.dbService = dbService;
    }

    public IOTDevice2 create(IOTDevice2 iot) {
        /*
        if(iot.getID() == null) {
            Long ID = this.id_generator.incrementAndGet();
            iot.setID(ID);
        }
         */
        dbService.persist(iot);
        return iot;
    }

    public List<IOTDevice2> getAllDevices() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT d FROM IOTDevice2 d");
        return query.getResultList();
    }

    public IOTDevice2 getDeviceByID(Long id) {
        EntityManager em = factory.createEntityManager();
        IOTDevice2 iot = em.find(IOTDevice2.class, Long.valueOf(id));
        return iot;
    }

    public IOTDevice2 delete(Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        IOTDevice2 iot = getDeviceByID(id);
        if (iot != null) {
           dbService.remove(iot);
        }
        return iot;    }

    public IOTDevice2 update(IOTDevice2 device, Long id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        if (getDeviceByID(id) != null) {
            IOTDevice2 updateDevice = getDeviceByID(id);
            updateDevice.setPoll(device.getPoll());
            dbService.merge(updateDevice);

            return updateDevice;
        }
        else {
            return null;
        }
    }
}
