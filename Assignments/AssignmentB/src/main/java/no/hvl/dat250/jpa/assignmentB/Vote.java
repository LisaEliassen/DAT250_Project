package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Poll.class)
    @JoinColumn(name = "poll_fk")
    private Poll poll;

    public Poll getPoll() {
        return this.poll;
    }

    public void setPoll(Poll owningPoll) {
        this.poll = owningPoll;
    }
}
