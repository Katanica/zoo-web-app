package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.NacinNabave;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Repository.NacinNabaveRepository;

import java.util.List;

@RestController
@RequestMapping("/api/nacin_nabave")
public class NacinNabaveController {

    private final NacinNabaveRepository repository;

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
    public ResponseEntity<NacinNabave> getById(@PathVariable Long id) {
        NacinNabave nacin = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Način nabave ne postoji: " + id));
        return ResponseEntity.ok(nacin);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<NacinNabave> create(@RequestBody NacinNabave nacinNabave) {
        return ResponseEntity.ok(repository.save(nacinNabave));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<NacinNabave> update(@PathVariable Long id, @RequestBody NacinNabave updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setOpisNabave(updated.getOpisNabave()); // prilagodi polja po potrebi
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Način nabave ne postoji: " + id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Način nabave ne postoji: " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}