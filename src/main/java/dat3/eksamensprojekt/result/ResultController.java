package dat3.eksamensprojekt.result;

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

    @PostMapping
    public ResponseEntity<ResultDTO> createResult(@RequestBody ResultDTO resultDTO) {
        ResultDTO createdResult = resultService.createResult(resultDTO);
        return ResponseEntity.ok(createdResult);

    }
}
