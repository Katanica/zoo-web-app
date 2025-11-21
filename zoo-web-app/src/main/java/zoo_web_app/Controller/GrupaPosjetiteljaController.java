package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.GrupaPosjetitelja;
import zoo_web_app.Service.GrupaPosjetiteljaService;

import java.util.List;

@RestController
@RequestMapping("/grupaPosjetitelja")
public class GrupaPosjetiteljaController {

    private final GrupaPosjetiteljaService grupaPosjetiteljaService;

    public GrupaPosjetiteljaController(GrupaPosjetiteljaService grupaPosjetiteljaService) {
        this.grupaPosjetiteljaService = grupaPosjetiteljaService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<GrupaPosjetitelja>> getAll() {
        return ResponseEntity.ok(grupaPosjetiteljaService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<GrupaPosjetitelja> getById(@PathVariable Long id) {
        return ResponseEntity.ok(grupaPosjetiteljaService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<GrupaPosjetitelja> create(@RequestBody GrupaPosjetitelja grupaPosjetitelja) {
        return ResponseEntity.ok(grupaPosjetiteljaService.create(grupaPosjetitelja));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<GrupaPosjetitelja> update(
            @PathVariable Long id,
            @RequestBody GrupaPosjetitelja grupaPosjetitelja
    ) {
        return ResponseEntity.ok(grupaPosjetiteljaService.update(id, grupaPosjetitelja));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        grupaPosjetiteljaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
