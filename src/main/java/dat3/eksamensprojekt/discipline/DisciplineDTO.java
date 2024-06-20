package dat3.eksamensprojekt.discipline;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DisciplineDTO {

        private Long id;
        private String name;
        private Discipline.ResultType resultType;


}
