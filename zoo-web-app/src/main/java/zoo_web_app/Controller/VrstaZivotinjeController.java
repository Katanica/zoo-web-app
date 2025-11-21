package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.VrstaZivotinje;
import zoo_web_app.Service.VrstaZivotinjeService;

import java.util.List;

@RestController
@RequestMapping("/api/vrste")
public class VrstaZivotinjeController {
    private final VrstaZivotinjeService vrstaService;

    public VrstaZivotinjeController(VrstaZivotinjeService vrstaService) {
        this.vrstaService = vrstaService;
    }

    @GetMapping
    public ResponseEntity<List<VrstaZivotinje>> getAll() {
        return ResponseEntity.ok(vrstaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VrstaZivotinje> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vrstaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VrstaZivotinje> create(@RequestBody VrstaZivotinje vrsta) {
        return ResponseEntity.ok(vrstaService.create(vrsta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VrstaZivotinje> update(
            @PathVariable Long id,
            @RequestBody VrstaZivotinje vrsta
    ) {
        return ResponseEntity.ok(vrstaService.update(id, vrsta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VrstaZivotinje> delete(@PathVariable Long id) {
        vrstaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}