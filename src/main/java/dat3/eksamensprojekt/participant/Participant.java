package dat3.eksamensprojekt.participant;

import dat3.eksamensprojekt.discipline.Discipline;
import dat3.eksamensprojekt.result.Result;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String club;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    @JoinTable(
            name = "participant_discipline",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id"))

private Set<Discipline> disciplines = new HashSet<>();

    @OneToMany(mappedBy = "participant")
    private Set<Result> results = new HashSet<>();




    public Participant() {
    }


    public Participant(String name, Gender gender, int age, String club) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.club = club;
    }


    public enum Gender {
        MALE,
        FEMALE
    }


}
