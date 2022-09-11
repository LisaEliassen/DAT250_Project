package no.hvl.dat250.jpa.assignment2.driver;

import no.hvl.dat250.jpa.assignment2.*;

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

        Person person = new Person();
        Address address = new Address();
        CreditCard creditCard1 = new CreditCard();
        CreditCard creditCard2 = new CreditCard();
        Pincode pincode = new Pincode();
        Bank bank = new Bank();

        // Name and address:
        person.setName("Max Mustermann");
        address.setStreet("Inndalsveien");
        address.setNumber(28);
        person.addAddress(address);
        address.addOwner(person);

        // Creditcards and pincode:
        creditCard1.setNumber(12345);
        creditCard1.setBalance(-5000);
        creditCard1.setLimit(-10000);
        creditCard2.setNumber(123);
        creditCard2.setBalance(1);
        creditCard2.setLimit(2000);
        pincode.setPincode("123");
        pincode.setCount(1);

        // Bank:
        bank.setName("Pengebank");
        bank.addOwnedCard(creditCard1);
        bank.addOwnedCard(creditCard2);

        // Creditcards:
        creditCard1.setOwningBank(bank);
        creditCard2.setOwningBank(bank);
        creditCard1.setPincode(pincode);
        creditCard2.setPincode(pincode);
        person.addCreditCard(creditCard1);
        person.addCreditCard(creditCard2);

        tx.begin();
        em.persist(bank);
        em.persist(creditCard1);
        em.persist(creditCard2);
        em.persist(pincode);
        em.persist(address);
        em.persist(person);
        tx.commit();

        em.close();
        factory.close();
    }
}
