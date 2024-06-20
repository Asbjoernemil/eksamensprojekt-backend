package dat3.eksamensprojekt.result;

import dat3.eksamensprojekt.discipline.DisciplineDTO;
import dat3.eksamensprojekt.participant.ParticipantDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {

        private Long id;
        private String resultValue;
        private Long participantId;
        private Long disciplineId;
        private LocalDate date;

        private ParticipantDTO participant;
        private DisciplineDTO discipline;

}
