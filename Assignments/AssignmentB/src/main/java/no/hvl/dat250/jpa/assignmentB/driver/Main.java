package no.hvl.dat250.jpa.assignmentB.driver;

import no.hvl.dat250.jpa.assignmentB.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static final String PERSISTENCE_UNIT_NAME = "AssignmentB";

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
        FeedAppUser feedAppUserAdmin2 = new FeedAppUser();

        feedAppUserAdmin2.setAdmin(true);
        feedAppUserAdmin2.setUsername("admin");
        feedAppUserAdmin2.setFirstName("Lisa Maria");
        feedAppUserAdmin2.setLastName("Eliassen");
        feedAppUserAdmin2.setPassword("passwordAdmin");

        //first user
        FeedAppUser feedAppUser1 = new FeedAppUser();

        feedAppUser1.setAdmin(false);
        feedAppUser1.setUsername("thums");
        feedAppUser1.setFirstName("Thomas");
        feedAppUser1.setLastName("Ulseth");
        feedAppUser1.setPassword("user1Password");

        //second user
        FeedAppUser feedAppUser2 = new FeedAppUser();

        feedAppUser2.setAdmin(false);
        feedAppUser2.setUsername("kevols");
        feedAppUser2.setFirstName("Kevin");
        feedAppUser2.setLastName("Olsen");
        feedAppUser2.setPassword("user2Password");

        //first IoT device
        IOTDevice iotDevice = new IOTDevice();
        iotDevice.setDeviceID(5L);

        //first poll owned by feedAppUser1
        Poll poll1 = new Poll();
        poll1.setID(195245L); //the id you need to search for this poll
        poll1.setUser(feedAppUser1);
        poll1.setName("CATS OR DOGS?"); //the name you need to search for this poll
        poll1.setCategory("Animals");
        poll1.setDescription("Poll to figure out which animal (cat or dog) people like the best.");
        poll1.setResult("Dog wins");

        //add the poll to it's owner
        feedAppUser1.setPoll(poll1);

        //first vote for poll1 (USER2)
        Vote vote1Poll1 = new Vote();
        vote1Poll1.setPoll(poll1);
        vote1Poll1.setVote("Dog");
        vote1Poll1.setUser(feedAppUser2);

        poll1.setVote(vote1Poll1);
        feedAppUser2.setVote(vote1Poll1);

        //second vote for poll1 (IOT)
        Vote vote2Poll1 = new Vote();
        vote2Poll1.setPoll(poll1);
        vote2Poll1.setVote("Dog");
        vote2Poll1.setIot(iotDevice);

        poll1.setVote(vote2Poll1);
        iotDevice.setVote(vote2Poll1);

        tx.commit();

        em.close();
        factory.close();
    }
}
