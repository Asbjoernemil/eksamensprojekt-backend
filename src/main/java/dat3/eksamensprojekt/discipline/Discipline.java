package dat3.eksamensprojekt.discipline;

import dat3.eksamensprojekt.participant.Participant;
import dat3.eksamensprojekt.result.Result;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    @ManyToMany(mappedBy = "disciplines")
    private Set<Participant> participants = new HashSet<>();

    @OneToMany(mappedBy = "discipline")
    private Set<Result> results = new HashSet<>();

    public Discipline(String name, ResultType resultType) {
        this.name = name;
        this.resultType = resultType;
    }

    public Discipline() {
    }

    public enum ResultType {
        TIME,
        DISTANCE,
        POINTS
    }

}
