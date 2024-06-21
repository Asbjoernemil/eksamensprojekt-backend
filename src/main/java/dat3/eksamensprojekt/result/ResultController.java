package dat3.eksamensprojekt.result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/results")

public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public ResponseEntity<List<ResultDTO>> getAllResults() {
        List<ResultDTO> results = resultService.getAllResults();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> getResultById(@PathVariable Long id) {
        ResultDTO resultDTO = resultService.getResultById(id);
        return resultDTO != null ? ResponseEntity.ok(resultDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<ResultDTO>> getResultsForParticipantById(@PathVariable Long participantId) {
        List<ResultDTO> results = resultService.getResultsByParticipantId(participantId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/participant/name/{name}")
    public ResponseEntity<List<ResultDTO>> getResultsForParticipantByName(@PathVariable String name) {
        List<ResultDTO> results = resultService.getResultsByParticipantName(name);
        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<ResultDTO> createResult(@RequestBody ResultDTO resultDTO) {
        ResultDTO createdResult = resultService.createResult(resultDTO);
        return ResponseEntity.ok(createdResult);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> updateResult(@PathVariable Long id, @RequestBody ResultDTO resultDTO) {
        try {
            ResultDTO updatedResult = resultService.updateResult(id, resultDTO);
            return ResponseEntity.ok(updatedResult);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResultDTO("Error updating result: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultDTO("Internal server error occurred"));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.ok().build();
    }


}
