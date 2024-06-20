package dat3.eksamensprojekt.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {

        private Long id;
        private String name;
        private String gender;
        private int age;
        private String club;


}
