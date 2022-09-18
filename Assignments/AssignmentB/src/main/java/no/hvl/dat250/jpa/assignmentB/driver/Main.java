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

        //admin password
        Password passwordAdmin = new Password();
        passwordAdmin.setPassword("admin");

        //first admin user
        FeedAppUser feedAppUserAdmin1 = new FeedAppUser();
        Device deviceAdmin1 = new Device();

        feedAppUserAdmin1.setAdmin(true);
        feedAppUserAdmin1.setUsername("admin");
        feedAppUserAdmin1.setFirstName("Olesya");
        feedAppUserAdmin1.setLastName("Pasichnyk");
        feedAppUserAdmin1.setPassword(passwordAdmin);

        deviceAdmin1.setDeviceID(1L);
        deviceAdmin1.setDeviceCookie("1234567890");
        deviceAdmin1.setTypeOfDevice("Laptop");
        deviceAdmin1.setUsers(feedAppUserAdmin1);

        feedAppUserAdmin1.setDevice(deviceAdmin1);

        //second admin user
        FeedAppUser feedAppUserAdmin2 = new FeedAppUser();
        Device deviceAdmin2 = new Device();

        feedAppUserAdmin2.setAdmin(true);
        feedAppUserAdmin2.setUsername("admin");
        feedAppUserAdmin2.setFirstName("Lisa Maria");
        feedAppUserAdmin2.setLastName("Eliassen");
        feedAppUserAdmin2.setPassword(passwordAdmin);

        deviceAdmin2.setDeviceID(2L);
        deviceAdmin2.setDeviceCookie("0987654321");
        deviceAdmin2.setTypeOfDevice("Desktop");
        deviceAdmin2.setUsers(feedAppUserAdmin2);

        feedAppUserAdmin2.setDevice(deviceAdmin2);

        //first user
        FeedAppUser feedAppUser1 = new FeedAppUser();
        Device user1Device = new Device();
        Password user1Password = new Password();
        user1Password.setPassword("sunset00..");

        feedAppUser1.setAdmin(false);
        feedAppUser1.setUsername("thums");
        feedAppUser1.setFirstName("Thomas");
        feedAppUser1.setLastName("Ulseth");
        feedAppUser1.setPassword(user1Password);

        user1Device.setDeviceID(3L);
        user1Device.setDeviceCookie("1230984567");
        user1Device.setTypeOfDevice("Desktop");
        user1Device.setUsers(feedAppUser1);

        feedAppUser1.setDevice(user1Device);

        //second user
        FeedAppUser feedAppUser2 = new FeedAppUser();
        Device user2Device = new Device();
        Password user2Password = new Password();
        user2Password.setPassword("sunrise99++");

        feedAppUser2.setAdmin(false);
        feedAppUser2.setUsername("kevols");
        feedAppUser2.setFirstName("Kevin");
        feedAppUser2.setLastName("Olsen");
        feedAppUser2.setPassword(user2Password);

        user2Device.setDeviceID(4L);
        user2Device.setDeviceCookie("0981237654");
        user2Device.setTypeOfDevice("Laptop");
        user2Device.setUsers(feedAppUser2);

        feedAppUser2.setDevice(user2Device);

        //first IoT device
        FeedAppUser feedAppUserIoT = new FeedAppUser();
        Device iotDevice = new Device();
        Password iotPassword = new Password();
        iotPassword.setPassword("");

        feedAppUserIoT.setAdmin(false);
        feedAppUserIoT.setUsername("IoT");
        feedAppUserIoT.setFirstName("");
        feedAppUserIoT.setLastName("");
        feedAppUserIoT.setPassword(iotPassword);

        iotDevice.setDeviceID(5L);
        iotDevice.setDeviceCookie("1357924680");
        iotDevice.setTypeOfDevice("IoT");
        iotDevice.setUsers(feedAppUserIoT);

        feedAppUserIoT.setDevice(iotDevice);

        //first poll owned by feedAppUser1
        Poll poll1 = new Poll();
        poll1.setPollID(195245L); //the id you need to search for this poll
        poll1.setUser(feedAppUser1);
        poll1.setPollName("CATS OR DOGS?"); //the name you need to search for this poll
        poll1.setCategory("Animals");
        poll1.setDescription("Poll to figure out which animal (cat or dog) people like the best.");
        poll1.setPollResult("Dog wins");

        //add the poll to it's owner
        feedAppUser1.setPoll(poll1);

        //first vote for poll1
        Vote vote1Poll1 = new Vote();
        vote1Poll1.setPoll(poll1);
        vote1Poll1.setVote("Dog");
        vote1Poll1.setUser(feedAppUser2);

        poll1.setVote(vote1Poll1);
        feedAppUser2.setVote(vote1Poll1);

        //second vote for poll1
        Vote vote2Poll1 = new Vote();
        vote2Poll1.setPoll(poll1);
        vote2Poll1.setVote("Dog");
        vote2Poll1.setUser(feedAppUserIoT);

        poll1.setVote(vote2Poll1);
        feedAppUserIoT.setVote(vote2Poll1);

        tx.commit();

        em.close();
        factory.close();
    }
}
