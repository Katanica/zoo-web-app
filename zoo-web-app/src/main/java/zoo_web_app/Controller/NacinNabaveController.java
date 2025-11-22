package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.NacinNabave;
import zoo_web_app.Entity.Radnik;
import zoo_web_app.Repository.NacinNabaveRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nacin-nabave")
public class NacinNabaveController {

    public final NacinNabaveRepository repository;

    public NacinNabaveController(NacinNabaveRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<NacinNabave>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<NacinNabave> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<NacinNabave> create(@RequestBody NacinNabave nacinNabave) {
        return ResponseEntity.ok(repository.save(nacinNabave));
    }
    /* DOVRŠIT!!!
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<NacinNabave> update(
            @PathVariable Long id,
            @RequestBody Radnik radnik
    ) {
        return ResponseEntity.ok(radnikService.update(id, radnik));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        radnikService.delete(id);
        return ResponseEntity.noContent().build();
    }
    */

}
