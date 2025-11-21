package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Obrazovanje;
import zoo_web_app.Service.ObrazovanjeService;

import java.util.List;

@RestController
@RequestMapping("/api/obrazovanje")
public class ObrazovanjeController {

    private final ObrazovanjeService obrazovanjeService;

    public ObrazovanjeController(ObrazovanjeService obrazovanjeService) {
        this.obrazovanjeService = obrazovanjeService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Obrazovanje>> getAll() {
        return ResponseEntity.ok(obrazovanjeService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Obrazovanje> getById(@PathVariable Long id) {
        return ResponseEntity.ok(obrazovanjeService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Obrazovanje> create(@RequestBody Obrazovanje obrazovanje) {
        return ResponseEntity.ok(obrazovanjeService.create(obrazovanje));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Obrazovanje> update(
            @PathVariable Long id,
            @RequestBody Obrazovanje obrazovanje
    ) {
        return ResponseEntity.ok(obrazovanjeService.update(id, obrazovanje));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        obrazovanjeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
