package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Nastamba;
import zoo_web_app.Service.NastambaService;

import java.util.List;

@RestController
@RequestMapping("/api/nastambe")
public class NastambaController {

    private final NastambaService nastambaService;

    public NastambaController(NastambaService nastambaService) {
        this.nastambaService = nastambaService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Nastamba>> getAll() {
        return ResponseEntity.ok(nastambaService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Nastamba> getById(@PathVariable Long id) {
        return ResponseEntity.ok(nastambaService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Nastamba> create(@RequestBody Nastamba nastamba) {
        return ResponseEntity.ok(nastambaService.create(nastamba));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Nastamba> update(
            @PathVariable Long id,
            @RequestBody Nastamba updated
    ) {
        return ResponseEntity.ok(nastambaService.update(id, updated));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nastambaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}