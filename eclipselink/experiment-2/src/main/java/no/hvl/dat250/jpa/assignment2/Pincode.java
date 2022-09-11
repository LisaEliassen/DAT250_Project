package no.hvl.dat250.jpa.assignment2;

import javax.persistence.*;

@Entity
public class Pincode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pincode;
    private Integer count;

    public Long getId() {
        return id;
    }

    public String getPincode() {
        return this.pincode;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
