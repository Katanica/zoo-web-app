package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Infrastruktura;
import zoo_web_app.Service.InfrastrukturaService;

import java.util.List;

@RestController
@RequestMapping("/api/infrastruktura")
public class InfrastrukturaController {

    private final InfrastrukturaService infrastrukturaService;

    public InfrastrukturaController(InfrastrukturaService infrastrukturaService) {
        this.infrastrukturaService = infrastrukturaService;
    }

    @GetMapping
    public ResponseEntity<List<Infrastruktura>> getAll() {
        return ResponseEntity.ok(infrastrukturaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Infrastruktura> getById(@PathVariable Long id) {
        return ResponseEntity.ok(infrastrukturaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Infrastruktura> create(@RequestBody Infrastruktura infrastruktura) {
        return ResponseEntity.ok(infrastrukturaService.create(infrastruktura));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Infrastruktura> update(
            @PathVariable Long id,
            @RequestBody Infrastruktura infrastruktura
    ) {
        return ResponseEntity.ok(infrastrukturaService.update(id, infrastruktura));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        infrastrukturaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}