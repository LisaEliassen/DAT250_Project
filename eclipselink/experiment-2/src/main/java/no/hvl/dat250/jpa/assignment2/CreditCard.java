package no.hvl.dat250.jpa.assignment2;

import javax.persistence.*;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Integer balance;
    private Integer limit;
    @ManyToOne
    @JoinColumn(name = "pincode_id")
    private Pincode pincode;
    @ManyToOne
    @JoinColumn(name = "owning_bank_id")
    private Bank owningBank;

    public Integer getNumber() {
        return null;
    }

    public Integer getBalance() {
        return null;
    }

    public Integer getLimit() {
        return null;
    }

    public Pincode getPincode() {
        return null;
    }

    public Bank getOwningBank() {
        return null;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public void setPincode(Pincode pincode) {
        this.pincode = pincode;
    }

    public void setOwningBank(Bank owningBank) {
        this.owningBank = owningBank;
    }
}
