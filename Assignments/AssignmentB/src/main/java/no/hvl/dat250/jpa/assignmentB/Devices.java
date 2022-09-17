package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Devices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String deviceID;

    private String typeOfDevice;

    private String deviceCookie;

    @ManyToMany(mappedBy = "devices")
    private Set<User> users = new HashSet<>();

}
