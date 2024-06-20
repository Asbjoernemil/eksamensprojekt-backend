package dat3.eksamensprojekt.discipline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {
    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDTO> getDisciplineById(@PathVariable Long id) {
        DisciplineDTO disciplineDTO = disciplineService.getDisciplineById(id);
        return disciplineDTO != null ? ResponseEntity.ok(disciplineDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public DisciplineDTO createDiscipline(@RequestBody DisciplineDTO disciplineDTO) {
        return disciplineService.createDiscipline(disciplineDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineDTO> updateDiscipline(@PathVariable Long id, @RequestBody DisciplineDTO disciplineDTO) {
        DisciplineDTO updatedDiscipline = disciplineService.updateDiscipline(id, disciplineDTO);
        return updatedDiscipline != null ? ResponseEntity.ok(updatedDiscipline) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.ok().build();
    }
}
