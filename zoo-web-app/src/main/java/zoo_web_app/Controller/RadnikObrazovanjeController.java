package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.RadnikObrazovanje;
import zoo_web_app.Service.RadnikObrazovanjeService;

import java.util.List;

@RestController
@RequestMapping("/api/radnikObrazovanje")
public class RadnikObrazovanjeController {

    private final RadnikObrazovanjeService radnikObrazovanjeService;

    public RadnikObrazovanjeController(RadnikObrazovanjeService radnikObrazovanjeService) {
        this.radnikObrazovanjeService = radnikObrazovanjeService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<RadnikObrazovanje>> getAll() {
        return ResponseEntity.ok(radnikObrazovanjeService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<RadnikObrazovanje> getById(@PathVariable Long id) {
        return ResponseEntity.ok(radnikObrazovanjeService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<RadnikObrazovanje> create(@RequestBody RadnikObrazovanje radnikObrazovanje) {
        return ResponseEntity.ok(radnikObrazovanjeService.create(radnikObrazovanje));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<RadnikObrazovanje> update(
            @PathVariable Long id,
            @RequestBody RadnikObrazovanje radnikObrazovanje
    ) {
        return ResponseEntity.ok(radnikObrazovanjeService.update(id, radnikObrazovanje));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        radnikObrazovanjeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
