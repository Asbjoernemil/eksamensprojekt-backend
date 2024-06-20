package dat3.eksamensprojekt.result;

import dat3.eksamensprojekt.discipline.Discipline;
import dat3.eksamensprojekt.discipline.DisciplineRepository;
import dat3.eksamensprojekt.participant.Participant;
import dat3.eksamensprojekt.participant.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;

    public ResultService(ResultRepository resultRepository, ParticipantRepository participantRepository, DisciplineRepository disciplineRepository) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public List<ResultDTO> getAllResults() {
        List<Result> results = resultRepository.findAll();
        return results.stream().map(this::convertToDTO).toList();
    }

    public ResultDTO getResultById(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Result not found with ID: " + id));
        return convertToDTO(result);
    }

    public ResultDTO createResult(ResultDTO resultDTO) {
        Participant participant = participantRepository.findById(resultDTO.getParticipantId())
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with ID: " + resultDTO.getParticipantId()));
        Discipline discipline = disciplineRepository.findById(resultDTO.getDisciplineId())
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + resultDTO.getDisciplineId()));

        Result result = new Result();
        result.setParticipant(participant);
        result.setDiscipline(discipline);
        result.setDate(resultDTO.getDate());
        result.setResultValue(resultDTO.getResultValue());

        resultRepository.save(result);

        return convertToDTO(result);
    }

    public ResultDTO updateResult(Long id, ResultDTO resultDTO) {
        Result existingResult = resultRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Result not found with ID: " + id));

        Participant participant = participantRepository.findById(resultDTO.getParticipantId())
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with ID: " + resultDTO.getParticipantId()));
        Discipline discipline = disciplineRepository.findById(resultDTO.getDisciplineId())
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + resultDTO.getDisciplineId()));

        existingResult.setParticipant(participant);
        existingResult.setDiscipline(discipline);
        existingResult.setDate(resultDTO.getDate());
        existingResult.setResultValue(resultDTO.getResultValue());

        resultRepository.save(existingResult);

        return convertToDTO(existingResult);
    }

    public void deleteResult(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Result not found with ID: " + id));
        resultRepository.delete(result);
    }



    public ResultDTO convertToDTO(Result result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(result.getId());
        resultDTO.setParticipantId(result.getParticipant().getId());
        resultDTO.setDisciplineId(result.getDiscipline().getId());
        resultDTO.setDate(result.getDate());
        resultDTO.setResultValue(result.getResultValue());

        return resultDTO;
    }




}
