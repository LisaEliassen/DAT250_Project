package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Integer balance;
    private Integer limit;
    @OneToOne
    @JoinColumn(name = "pincode_fk")
    private Password password;
    @ManyToOne(targetEntity = Poll.class)
    @JoinColumn(name = "bank_fk")
    private Poll owningPoll;

    public Integer getNumber() {
        return this.number;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public Password getPincode() {
        return this.password;
    }

    public Poll getOwningBank() {
        return this.owningPoll;
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
    public void setPincode(Password password) {
        this.password = password;
    }

    public void setOwningBank(Poll owningPoll) {
        this.owningPoll = owningPoll;
    }
}
