package no.hvl.dat250.jpa.assignment2.driver;

import javax.persistence.*;
/**
 * This file contains means to clear the database, if JPA's drop-table actions runs into inconsistencies as shown in the lectures
 */
public class DatabaseInitializer {
	private static final String PERSISTENCE_UNIT_NAME = ".";  // Your unit name here, see persistence.xml!!
    private static EntityManagerFactory factory;
	public static void main(String[] args) { 
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
// 1. Make sure that you disable the persistable classes in the persistence.xml before executing this (elements <class> ... </class>)
// 2. Now make sure to first remove those tables that are not referenced by a foreign key (e.g. join tables) from some other table, afterwards remove all others				

// Example:		
		em.createNativeQuery("DROP TABLE PERSON_ADDRESS").executeUpdate();
		em.createNativeQuery("DROP TABLE PERSON").executeUpdate();
		em.createNativeQuery("DROP TABLE ADDRESS").executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
// 3. Now re-enable the persistable classes, which you disabled in 1.!!		
	}
}
