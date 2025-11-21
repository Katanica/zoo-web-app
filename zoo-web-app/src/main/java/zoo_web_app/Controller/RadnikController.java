package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Radnik;
import zoo_web_app.Service.RadnikService;

import java.util.List;

@RestController
@RequestMapping("/radnik")
public class RadnikController {

    private final RadnikService radnikService;

    public RadnikController(RadnikService radnikService) {
        this.radnikService = radnikService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Radnik>> getAll() {
        return ResponseEntity.ok(radnikService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Radnik> getById(@PathVariable Long id) {
        return ResponseEntity.ok(radnikService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Radnik> create(@RequestBody Radnik radnik) {
        return ResponseEntity.ok(radnikService.create(radnik));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Radnik> update(
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
}
