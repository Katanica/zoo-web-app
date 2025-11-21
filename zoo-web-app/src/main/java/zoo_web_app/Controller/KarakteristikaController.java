package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Karakteristika;
import zoo_web_app.Service.KarakteristikaService;

import java.util.List;

@RestController
@RequestMapping("/api/karakteristike")
public class KarakteristikaController {

    private final KarakteristikaService karakteristikaService;

    public KarakteristikaController(KarakteristikaService karakteristikaService) {
        this.karakteristikaService = karakteristikaService;
    }

    @GetMapping
    public ResponseEntity<List<Karakteristika>> getAll() {
        return ResponseEntity.ok(karakteristikaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Karakteristika> getById(@PathVariable Long id) {
        return ResponseEntity.ok(karakteristikaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Karakteristika> create(@RequestBody Karakteristika karakteristika) {
        return ResponseEntity.ok(karakteristikaService.create(karakteristika));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Karakteristika> update(
            @PathVariable Long id,
            @RequestBody Karakteristika karakteristika
    ) {
        return ResponseEntity.ok(karakteristikaService.update(id, karakteristika));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        karakteristikaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}