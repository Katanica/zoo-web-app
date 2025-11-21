package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.VrstaIncidenta;
import zoo_web_app.Service.VrstaIncidentaService;

import java.util.List;

@RestController
@RequestMapping("/api/vrsta-incidenta")
public class VrstaIncidentaController {

    private final VrstaIncidentaService vrstaIncidentaService;

    public VrstaIncidentaController(VrstaIncidentaService vrstaIncidentaService) {
        this.vrstaIncidentaService = vrstaIncidentaService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<VrstaIncidenta>> getAll() {
        return ResponseEntity.ok(vrstaIncidentaService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<VrstaIncidenta> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vrstaIncidentaService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<VrstaIncidenta> create(@RequestBody VrstaIncidenta vrstaIncidenta) {
        return ResponseEntity.ok(vrstaIncidentaService.create(vrstaIncidenta));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<VrstaIncidenta> update(
            @PathVariable Long id,
            @RequestBody VrstaIncidenta vrstaIncidenta) {
        return ResponseEntity.ok(vrstaIncidentaService.update(id, vrstaIncidenta));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vrstaIncidentaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}