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
        Participant participant = participantRepository.findByName(resultDTO.getParticipantName())
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with name: " + resultDTO.getParticipantName()));
        Discipline discipline = disciplineRepository.findByName(resultDTO.getDisciplineName())
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with name: " + resultDTO.getDisciplineName()));

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


        if (resultDTO.getParticipantName() == null || resultDTO.getParticipantName().isEmpty()) {
            throw new IllegalArgumentException("Participant name is null or empty");
        }


        if (resultDTO.getDisciplineName() == null || resultDTO.getDisciplineName().isEmpty()) {
            throw new IllegalArgumentException("Discipline name is null or empty");
        }


        Participant participant = participantRepository.findByName(resultDTO.getParticipantName())
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with name: " + resultDTO.getParticipantName()));


        Discipline discipline = disciplineRepository.findByName(resultDTO.getDisciplineName())
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with name: " + resultDTO.getDisciplineName()));


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

    public List<ResultDTO> getResultsByParticipantName(String name) {
        List<Result> results = resultRepository.findByParticipantName(name);
        return results.stream().map(this::convertToDTO).toList();
    }

    public List<ResultDTO> getResultsByDisciplineName(String name) {
        List<Result> results = resultRepository.findByDisciplineName(name);
        return results.stream().map(this::convertToDTO).toList();
    }



    public ResultDTO convertToDTO(Result result) {
           ResultDTO dto = new ResultDTO();
            dto.setId(result.getId());
            dto.setParticipantName(result.getParticipant().getName());
            dto.setDisciplineName(result.getDiscipline().getName());
            dto.setDate(result.getDate());
            dto.setResultValue(result.getResultValue());

            return dto;
        }


    public List<ResultDTO> getResultsByParticipantId(Long participantId) {
        List<Result> results = resultRepository.findByParticipantId(participantId);
        return results.stream().map(this::convertToDTO).toList();
    }
}
