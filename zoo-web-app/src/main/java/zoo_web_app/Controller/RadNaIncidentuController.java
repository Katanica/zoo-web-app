package zoo_web_app.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.RadNaIncidentu;
import zoo_web_app.Service.RadNaIncidentuService;

import java.util.List;

@RestController
@RequestMapping("/api/rad-incident")
@RequiredArgsConstructor
public class RadNaIncidentuController {

    private final RadNaIncidentuService radService;

    @PostMapping
    public ResponseEntity<RadNaIncidentu> create(@RequestBody RadNaIncidentu rad) {
        return ResponseEntity.ok(radService.create(rad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RadNaIncidentu> update(
            @PathVariable Long id,
            @RequestBody RadNaIncidentu rad) {
        return ResponseEntity.ok(radService.update(id, rad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        radService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RadNaIncidentu> findById(@PathVariable Long id) {
        return ResponseEntity.ok(radService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RadNaIncidentu>> findAll() {
        return ResponseEntity.ok(radService.findAll());
    }

    @GetMapping("/incident/{incidentId}")
    public ResponseEntity<List<RadNaIncidentu>> findByIncident(@PathVariable Long incidentId) {
        return ResponseEntity.ok(radService.findByIncident(incidentId));
    }
}
