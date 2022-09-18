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
        User userAdmin1 = new User();
        Device deviceAdmin1 = new Device();

        userAdmin1.setAdmin(true);
        userAdmin1.setUsername("admin");
        userAdmin1.setFirstName("Olesya");
        userAdmin1.setLastName("Pasichnyk");
        userAdmin1.setPassword(passwordAdmin);

        deviceAdmin1.setDeviceID("1");
        deviceAdmin1.setDeviceCookie("1234567890");
        deviceAdmin1.setTypeOfDevice("Laptop");
        deviceAdmin1.setUsers(userAdmin1);

        userAdmin1.setDevice(deviceAdmin1);

        //second admin user
        User userAdmin2 = new User();
        Device deviceAdmin2 = new Device();

        userAdmin2.setAdmin(true);
        userAdmin2.setUsername("admin");
        userAdmin2.setFirstName("Lisa Maria");
        userAdmin2.setLastName("Eliassen");
        userAdmin2.setPassword(passwordAdmin);

        deviceAdmin2.setDeviceID("2");
        deviceAdmin2.setDeviceCookie("0987654321");
        deviceAdmin2.setTypeOfDevice("Desktop");
        deviceAdmin2.setUsers(userAdmin2);

        userAdmin2.setDevice(deviceAdmin2);

        //first user
        User user1 = new User();
        Device user1Device = new Device();
        Password user1Password = new Password();
        user1Password.setPassword("sunset00..");

        user1.setAdmin(false);
        user1.setUsername("thums");
        user1.setFirstName("Thomas");
        user1.setLastName("Ulseth");
        user1.setPassword(user1Password);

        user1Device.setDeviceID("3");
        user1Device.setDeviceCookie("1230984567");
        user1Device.setTypeOfDevice("Desktop");
        user1Device.setUsers(user1);

        user1.setDevice(user1Device);

        //second user
        User user2 = new User();
        Device user2Device = new Device();
        Password user2Password = new Password();
        user2Password.setPassword("sunrise99++");

        user2.setAdmin(false);
        user2.setUsername("kevols");
        user2.setFirstName("Kevin");
        user2.setLastName("Olsen");
        user2.setPassword(user2Password);

        user2Device.setDeviceID("4");
        user2Device.setDeviceCookie("0981237654");
        user2Device.setTypeOfDevice("Laptop");
        user2Device.setUsers(user2);

        user2.setDevice(user2Device);

        //first IoT device
        User userIoT = new User();
        Device iotDevice = new Device();
        Password iotPassword = new Password();
        iotPassword.setPassword("");

        userIoT.setAdmin(false);
        userIoT.setUsername("IoT");
        userIoT.setFirstName("");
        userIoT.setLastName("");
        userIoT.setPassword(iotPassword);

        iotDevice.setDeviceID("5");
        iotDevice.setDeviceCookie("1357924680");
        iotDevice.setTypeOfDevice("IoT");
        iotDevice.setUsers(userIoT);

        userIoT.setDevice(iotDevice);

        //first poll owned by user1
        Poll poll1 = new Poll();
        poll1.setPollID(195245L); //the id you need to search for this poll
        poll1.setUser(user1);
        poll1.setPollName("CATS OR DOGS?"); //the name you need to search for this poll
        poll1.setCategory("Animals");
        poll1.setDescription("Poll to figure out which animal (cat or dog) people like the best.");
        poll1.setPollResult("Dog wins");

        //add the poll to it's owner
        user1.setPoll(poll1);

        //first vote for poll1
        Vote vote1Poll1 = new Vote();
        vote1Poll1.setPoll(poll1);
        vote1Poll1.setVote("Dog");
        vote1Poll1.setUser(user2);

        poll1.setVote(vote1Poll1);
        user2.setVote(vote1Poll1);

        //second vote for poll1
        Vote vote2Poll1 = new Vote();
        vote2Poll1.setPoll(poll1);
        vote2Poll1.setVote("Dog");
        vote2Poll1.setUser(userIoT);

        poll1.setVote(vote2Poll1);
        userIoT.setVote(vote2Poll1);

        tx.commit();

        em.close();
        factory.close();
    }
}
