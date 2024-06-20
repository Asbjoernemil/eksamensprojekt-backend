package dat3.eksamensprojekt.participant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

     @GetMapping("/all")
    public List<ParticipantDTO> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public ResponseEntity <ParticipantDTO> getParticipantById(@PathVariable Long id) {
        ParticipantDTO participantDTO = participantService.getParticipantById(id);
        if (participantDTO != null) {
            return ResponseEntity.ok(participantDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ParticipantDTO createParticipant(@RequestBody ParticipantDTO participant) {
        return participantService.createParticipant(participant);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ParticipantDTO> updateParticipant(@PathVariable Long id, @RequestBody ParticipantDTO participant) {
        ParticipantDTO updatedParticipant = participantService.updateParticipant(id, participant);
        return updatedParticipant != null ? ResponseEntity.ok(updatedParticipant) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.ok().build();
    }
}
