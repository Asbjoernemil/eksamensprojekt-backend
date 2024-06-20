package dat3.eksamensprojekt.discipline;

import dat3.eksamensprojekt.participant.Participant;
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
    private String resultType;

    @ManyToMany(mappedBy = "discipline")
    private Set<Participant> participants = new HashSet<>();

    public Discipline(String name, String resultType) {
        this.name = name;
        this.resultType = resultType;
    }

    public Discipline() {
    }

}
