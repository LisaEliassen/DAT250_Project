package no.hvl.dat250.jpa.assignmentBC.driver;

import no.hvl.dat250.jpa.assignmentBC.model.FeedAppUser;
import no.hvl.dat250.jpa.assignmentBC.model.IOTDevice;
import no.hvl.dat250.jpa.assignmentBC.model.Poll;
import no.hvl.dat250.jpa.assignmentBC.model.Vote;

import javax.persistence.*;

public class Main {
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB-C";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        //first admin user
        FeedAppUser feedAppUserAdmin1 = new FeedAppUser();

        feedAppUserAdmin1.setAdmin(true);
        feedAppUserAdmin1.setUsername("admin");
        feedAppUserAdmin1.setFirstName("Olesya");
        feedAppUserAdmin1.setLastName("Pasichnyk");
        feedAppUserAdmin1.setPassword("passwordAdmin");

        //second admin user
        FeedAppUser feedAppUser1 = new FeedAppUser();

        //feedAppUserAdmin2.setAdmin(true);
        feedAppUser1.setUsername("lel");
        feedAppUser1.setFirstName("Lisa Maria");
        feedAppUser1.setLastName("Eliassen");
        feedAppUser1.setPassword("passwordAdmin");

        //first user
        FeedAppUser feedAppUser2 = new FeedAppUser();

        feedAppUser2.setAdmin(false);
        feedAppUser2.setUsername("thums");
        feedAppUser2.setFirstName("Thomas");
        feedAppUser2.setLastName("Ulseth");
        feedAppUser2.setPassword("user1Password");

        //first IoT device
        IOTDevice iotDevice = new IOTDevice();
        iotDevice.setID(5L);

        //first poll owned by feedAppUser1
        Poll poll1 = new Poll();
        poll1.setID(1000L); //the id you need to search for this poll
        poll1.setUser(feedAppUser1.getID());
        poll1.setName("CATS OR DOGS?"); //the name you need to search for this poll
        poll1.setCategory("Animals");
        poll1.setDescription("Poll to figure out which animal (cat or dog) people like the best.");
        poll1.setResult("Dog wins");
        poll1.addIoT(iotDevice.getID());

        //add the poll to it's owner
        feedAppUser1.addPoll(poll1.getID());

        //first vote for poll1 (USER2)
        Vote vote1Poll1 = new Vote();
        vote1Poll1.setID(1L);
        vote1Poll1.setPoll(poll1.getID());
        vote1Poll1.setVote("Dog");
        vote1Poll1.setUser(feedAppUser2.getID());

        poll1.addVote(vote1Poll1.getID());
        feedAppUser2.addVote(vote1Poll1.getID());
        /*
        //second vote for poll1 (IOT)
        Vote vote2Poll1 = new Vote();
        vote2Poll1.setPoll(poll1.getID());
        vote2Poll1.setVote("Dog");
        //vote2Poll1.setIot(iotDevice);

        poll1.addVote(vote2Poll1.getID());
        iotDevice.addVote(vote2Poll1.getID());*/

        em.persist(feedAppUserAdmin1);
        em.persist(feedAppUser1);
        em.persist(feedAppUser2);
        em.persist(iotDevice);
        em.persist(poll1);
        //em.persist(vote1Poll1);

        tx.commit();


        Query query = em.createQuery("SELECT p FROM FeedAppUser p");
        Query query2 = em.createQuery("SELECT p FROM Poll p");

        System.out.println(query2.getResultList());
        em.close();
        factory.close();
    }
}
