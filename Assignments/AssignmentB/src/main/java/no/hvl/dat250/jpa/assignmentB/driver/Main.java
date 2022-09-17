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

        //tx.begin();

        //tx.commit();

        //em.close();
        //factory.close();
    }
}
