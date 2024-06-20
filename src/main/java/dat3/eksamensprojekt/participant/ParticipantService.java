package dat3.eksamensprojekt.participant;

import dat3.eksamensprojekt.discipline.Discipline;
import dat3.eksamensprojekt.discipline.DisciplineDTO;
import dat3.eksamensprojekt.discipline.DisciplineRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;


 public ParticipantService(ParticipantRepository participantRepository, DisciplineRepository disciplineRepository) {
          this.participantRepository = participantRepository;
     this.disciplineRepository = disciplineRepository;
 }

public List<ParticipantDTO> getAllParticipants() {
        return participantRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public ParticipantDTO convertToDTO(Participant participant) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setId(participant.getId());
        participantDTO.setName(participant.getName());
        participantDTO.setAge(participant.getAge());
        participantDTO.setGender(participant.getGender());
        participantDTO.setClub(participant.getClub());

        return participantDTO;
    }

    public ParticipantDTO getParticipantById(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with ID: " + id));
        return convertToDTO(participant);
    }

    public ParticipantDTO createParticipant(ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setId(participantDTO.getId());
        participant.setName(participantDTO.getName());
        participant.setAge(participantDTO.getAge());
        participant.setGender(participantDTO.getGender());
        participant.setClub(participantDTO.getClub());

        Set<Discipline> disciplines = new HashSet<>();
        if (participantDTO.getDiscipline() != null) {
            for (DisciplineDTO disciplineDTO : participantDTO.getDiscipline()) {
                Discipline discipline = disciplineRepository.findById(disciplineDTO.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + disciplineDTO.getId()));
                disciplines.add(discipline);
            }
        }

        Participant savedParticipant = participantRepository.save(participant);
        return convertToDTO(savedParticipant); // Convert and return the saved participant as DTO
    }

    public ParticipantDTO updateParticipant(Long id, ParticipantDTO participantDTO) {

        Participant existingParticipant = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with ID: " + id));


        existingParticipant.setName(participantDTO.getName());
        existingParticipant.setAge(participantDTO.getAge());
        existingParticipant.setGender(participantDTO.getGender());
        existingParticipant.setClub(participantDTO.getClub());


        Set<Discipline> updatedDisciplines = new HashSet<>();
        if (participantDTO.getDiscipline() != null) {
            for (DisciplineDTO disciplineDTO : participantDTO.getDiscipline()) {

                Discipline discipline = disciplineRepository.findById(disciplineDTO.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + disciplineDTO.getId()));


                if (!existingParticipant.getDisciplines().contains(discipline)) {
                    updatedDisciplines.add(discipline);
                }
            }
        }


        existingParticipant.getDisciplines().removeIf(d -> !updatedDisciplines.contains(d));


        Participant savedParticipant = participantRepository.save(existingParticipant);

        return convertToDTO(savedParticipant);
    }

    public List<ParticipantDTO> getParticipantsByClub(String club) {
        List<Participant> participants = participantRepository.findByClub(club);
        return participants.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public List<ParticipantDTO> searchParticipants(String name) {
        return participantRepository.findAll().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ParticipantDTO deleteParticipant(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with ID: " + id));
        participantRepository.delete(participant);
        return convertToDTO(participant);
    }


    public List<ParticipantDTO> getParticipantsByGender(Participant.Gender gender) {
        List<Participant> participants = participantRepository.findByGender(gender);
        return participants.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ParticipantDTO> getParticipantsByDiscipline(Long disciplineId) {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + disciplineId));
        return discipline.getParticipants().stream().map(this::convertToDTO).collect(Collectors.toList());
    }


}
