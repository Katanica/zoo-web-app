package zoo_web_app.Controller;

import ch.qos.logback.core.status.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.DTO.ObavezaFrontend;
import zoo_web_app.Entity.Obaveza;
import zoo_web_app.Service.ObavezaService;

import java.util.List;

@RestController
@RequestMapping("/api/obaveza")
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

    @GetMapping("/broj")
    public long brojObaveza(){
        return obavezaService.brojObaveza();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Obaveza> getById(@PathVariable Long id) {
        return ResponseEntity.ok(obavezaService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Obaveza> create(@RequestBody ObavezaFrontend obaveza) {
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

    @GetMapping("/aktivne")
    public ResponseEntity<List<Obaveza>> aktivne() {
        return ResponseEntity.ok(obavezaService.getAktivne());
    }

    @GetMapping("/prosle")
    public ResponseEntity<List<Obaveza>> prosle(){
        return ResponseEntity.ok(obavezaService.getProsle());
    }

    @PatchMapping("/{id}/zavrsi")
    public ResponseEntity<Obaveza> zavrsi(@PathVariable Long id) {
        return ResponseEntity.ok(obavezaService.zavrsi(id));
    }
}
