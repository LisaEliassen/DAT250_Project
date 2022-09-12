package no.hvl.dat250.assignment2.driver;

import no.hvl.dat250.jpa.assignment2.*;
import no.hvl.dat250.jpa.assignment2.driver.Main;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest {

    private EntityManagerFactory factory;

    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory(Main.PERSISTENCE_UNIT_NAME);
    }

    /**
     * Try to delete the db directory if you get SQL-Errors.
     */
    @Test
    public void testDomainModelPersistence() {
        // Run the main class to persist the objects.
        Main.main(new String[]{});

        EntityManager em = factory.createEntityManager();

        // Load person
        Person person = em.find(Person.class, 1L);

        // Test person data
        assertThat(person.getName(), is("Max Mustermann"));

        // Test address
        assertThat(person.getAddresses().size(), is(1));
        Address address = person.getAddresses().iterator().next();

        assertThat(address.getStreet(), is("Inndalsveien"));
        assertThat(address.getNumber(), is(28));
        assertThat(address.getOwners(), is(Set.of(person)));

        // Test credit cards
        assertThat(person.getCreditCards().size(), is(2));
        CreditCard firstCard = getCardWithNumber(person, 12345);
        CreditCard secondCard = getCardWithNumber(person, 123);

        assertThat(firstCard.getNumber(), is(12345));
        assertThat(firstCard.getBalance(), is(-5000));
        assertThat(firstCard.getLimit(), is(-10000));

        assertThat(secondCard.getNumber(), is(123));
        assertThat(secondCard.getBalance(), is(1));
        assertThat(secondCard.getLimit(), is(2000));

        // Test pincode
        Pincode firstCardPincode = firstCard.getPincode();

        assertThat(firstCardPincode.getId(), is(secondCard.getPincode().getId())); // Pincode objects of the two cards are identical!
        assertThat(firstCardPincode.getPincode(), is("123"));
        assertThat(firstCardPincode.getCount(), is(1));

        // Test bank
        Bank bank = firstCard.getOwningBank();
        assertThat(bank.getId(), is(secondCard.getOwningBank().getId())); // Bank objects of the two cards are identical!
        assertThat(bank.getName(), is("Pengebank"));
        assertThat(bank.getOwnedCards(), is(Set.of(firstCard, secondCard)));
    }

    private CreditCard getCardWithNumber(Person person, int cardNumber) {
        Optional<CreditCard> optionalCard = person.getCreditCards().stream()
                .filter(creditCard -> creditCard.getNumber() == cardNumber)
                .findFirst();
        if (optionalCard.isEmpty()) {
            throw new RuntimeException(
                    String.format("Card with number %s was not found for the person %s!",
                            cardNumber,
                            person.getName()));
        }
        return optionalCard.get();
    }
}