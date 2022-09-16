package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;
import java.util.HashMap;

@Entity
public class PollResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String result;



}
