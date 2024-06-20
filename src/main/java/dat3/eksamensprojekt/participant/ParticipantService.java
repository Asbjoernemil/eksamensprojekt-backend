package dat3.eksamensprojekt.participant;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

 public ParticipantService(ParticipantRepository participantRepository) {
          this.participantRepository = participantRepository;
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
        Participant savedParticipant = participantRepository.save(participant); // Save the participant
        return convertToDTO(savedParticipant); // Convert and return the saved participant as DTO
    }

    public ParticipantDTO updateParticipant(Long id, ParticipantDTO participant) {
        Participant existingParticipant = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with ID: " + id));
        existingParticipant.setName(participant.getName());
        existingParticipant.setAge(participant.getAge());
        existingParticipant.setGender(participant.getGender());
        existingParticipant.setClub(participant.getClub());
        Participant savedParticipant = participantRepository.save(existingParticipant); // Save the participant
        return convertToDTO(savedParticipant); // Convert and return the saved participant as DTO
    }

    public List<ParticipantDTO> getParticipantsByClub(String club) {
        List<Participant> participants = participantRepository.findByClub(club);
        return participants.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ParticipantDTO deleteParticipant(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with ID: " + id));
        participantRepository.delete(participant);
        return convertToDTO(participant);
    }



}
