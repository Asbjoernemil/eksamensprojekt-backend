package dat3.eksamensprojekt.result;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByParticipantName(String name);
    List<Result> findByParticipantId(Long participantId);
    List<Result> findByDisciplineId(Long disciplineId);
    List<Result> findByDisciplineName(String name);

}
