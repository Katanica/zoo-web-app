package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Obaveza;
import zoo_web_app.Service.ObavezaService;

import java.util.List;

@RestController
@RequestMapping("/obaveza")
public class ObavezaController {

    private final ObavezaService obavezaService;

    public ObavezaController(ObavezaService obavezaService) {
        this.obavezaService = obavezaService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Obaveza>> getAll() {
        return ResponseEntity.ok(obavezaService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Obaveza> getById(@PathVariable Long id) {
        return ResponseEntity.ok(obavezaService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Obaveza> create(@RequestBody Obaveza obaveza) {
        return ResponseEntity.ok(obavezaService.create(obaveza));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Obaveza> update(
            @PathVariable Long id,
            @RequestBody Obaveza obaveza
    ) {
        return ResponseEntity.ok(obavezaService.update(id, obaveza));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        obavezaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
