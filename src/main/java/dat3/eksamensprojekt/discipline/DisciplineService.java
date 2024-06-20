package dat3.eksamensprojekt.discipline;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;


    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DisciplineDTO getDisciplineById(Long id) {
        Discipline discipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + id));
        return convertToDTO(discipline);
    }

    public DisciplineDTO createDiscipline(DisciplineDTO disciplineDTO) {
        Discipline discipline = new Discipline();
        discipline.setId(disciplineDTO.getId());
        discipline.setName(disciplineDTO.getName());
        discipline.setResultType(disciplineDTO.getResultType());
        Discipline savedDiscipline = disciplineRepository.save(discipline); // Save the discipline
        return convertToDTO(savedDiscipline); // Convert and return the saved discipline as DTO
    }

    public DisciplineDTO updateDiscipline(Long id, DisciplineDTO discipline) {
        Discipline existingDiscipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + id));
        existingDiscipline.setName(discipline.getName());
        existingDiscipline.setResultType(discipline.getResultType());
        Discipline savedDiscipline = disciplineRepository.save(existingDiscipline); // Save the discipline
        return convertToDTO(savedDiscipline); // Convert and return the saved discipline as DTO
    }

    public DisciplineDTO deleteDiscipline(Long id) {
        disciplineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + id));
        disciplineRepository.deleteById(id);

    }

    public DisciplineDTO convertToDTO(Discipline discipline) {
        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(discipline.getId());
        disciplineDTO.setName(discipline.getName());
        disciplineDTO.setResultType(discipline.getResultType());

        return disciplineDTO;
    }
}
