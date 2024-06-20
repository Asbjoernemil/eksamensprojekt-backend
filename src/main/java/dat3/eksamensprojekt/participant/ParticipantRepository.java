package dat3.eksamensprojekt.participant;

import dat3.eksamensprojekt.discipline.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findByClub(String club);

    Optional<Participant> findByName(String name);

    List<Participant> findByGender(Participant.Gender gender);

    List<Participant> findByDisciplines(Set<Discipline> disciplines);
}
