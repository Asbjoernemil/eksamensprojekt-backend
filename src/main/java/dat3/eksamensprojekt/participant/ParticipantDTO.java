package dat3.eksamensprojekt.participant;

import dat3.eksamensprojekt.discipline.Discipline;
import dat3.eksamensprojekt.discipline.DisciplineDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {

        private Long id;
        private String name;
        private Participant.Gender gender;
        private int age;
        private String club;
        private Set<DisciplineDTO> discipline;

}
