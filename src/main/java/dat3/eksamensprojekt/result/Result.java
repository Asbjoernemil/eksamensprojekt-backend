package dat3.eksamensprojekt.result;

import dat3.eksamensprojekt.discipline.Discipline;
import dat3.eksamensprojekt.participant.Participant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    private LocalDate date;
    private String resultValue;

    public Result(Participant participant, Discipline discipline, LocalDate date, String resultValue) {
        this.participant = participant;
        this.discipline = discipline;
        this.date = date;
        this.resultValue = resultValue;
    }

    public Result() {
    }

}
