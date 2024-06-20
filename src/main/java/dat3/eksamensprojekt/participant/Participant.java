package dat3.eksamensprojekt.participant;

import dat3.eksamensprojekt.discipline.Discipline;
import dat3.eksamensprojekt.result.Result;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    private LocalDate birthDate;
    private String club;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    @JoinTable(
            name = "participant_discipline",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id"))

private Set<Discipline> disciplines = new HashSet<>();

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Result> results = new HashSet<>();




    public Participant() {
    }


    public Participant(String name, Gender gender, LocalDate birthDate, String club) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.club = club;
    }


    public enum Gender {
        MALE,
        FEMALE
    }


}
