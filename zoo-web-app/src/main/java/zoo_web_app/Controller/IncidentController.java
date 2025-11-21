package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Incident;
import zoo_web_app.Service.IncidentService;

import java.util.List;

@RestController
@RequestMapping("/api/incident")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Incident>> getAll() {
        return ResponseEntity.ok(incidentService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Incident> getById(@PathVariable Long id) {
        return ResponseEntity.ok(incidentService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Incident> create(@RequestBody Incident incident) {
        return ResponseEntity.ok(incidentService.create(incident));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Incident> update(
            @PathVariable Long id,
            @RequestBody Incident incident
    ) {
        return ResponseEntity.ok(incidentService.update(id, incident));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        incidentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
