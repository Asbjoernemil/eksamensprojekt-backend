package dat3.eksamensprojekt.discipline;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DisciplineDTO {

        private Long id;
        private String name;
        private Discipline.ResultType resultType;
        private Set<String> participantsNames = new HashSet<>();

        public DisciplineDTO(Discipline discipline) {
        }

        public void setParticipantNames(Set<String> participantsNames) {
                this.participantsNames = participantsNames;

        }

        public DisciplineDTO(Long id, String name, Discipline.ResultType resultType) {
                this.id = id;
                this.name = name;
                this.resultType = resultType;
        }
}
