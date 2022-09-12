package no.hvl.dat250.jpa.assignmentB.driver;

import no.hvl.dat250.jpa.assignmentB.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static final String PERSISTENCE_UNIT_NAME = "experiment2";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        VotingUser votingUser = new VotingUser();
        PollResult pollResult = new PollResult();
        Vote vote1 = new Vote();
        Vote vote2 = new Vote();
        Password password = new Password();
        Poll poll = new Poll();

        // Name and pollResult:
        votingUser.setName("Max Mustermann");
        pollResult.setStreet("Inndalsveien");
        pollResult.setNumber(28);
        pollResult.addOwner(votingUser);

        // Creditcards and password:
        vote1.setNumber(12345);
        vote1.setBalance(-5000);
        vote1.setLimit(-10000);
        vote2.setNumber(123);
        vote2.setBalance(1);
        vote2.setLimit(2000);
        password.setPincode("123");
        password.setCount(1);

        // Poll:
        poll.setName("Pengebank");
        poll.addOwnedCard(vote1);
        poll.addOwnedCard(vote2);

        // Creditcards:
        vote1.setOwningBank(poll);
        vote2.setOwningBank(poll);
        vote1.setPincode(password);
        vote2.setPincode(password);

        tx.begin();
        em.persist(poll);
        em.persist(vote1);
        em.persist(vote2);
        em.persist(password);
        em.persist(pollResult);
        em.persist(votingUser);
        tx.commit();

        em.close();
        factory.close();
    }
}
