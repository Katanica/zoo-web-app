package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Jedinka;
import zoo_web_app.Service.JedinkaService;

import java.util.List;

@RestController
@RequestMapping("/api/jedinke")
public class JedinkaController {

    private final JedinkaService jedinkaService;

    public JedinkaController(JedinkaService jedinkaService) {
        this.jedinkaService = jedinkaService;
    }

    // GET ALL – vrati sve jedinke
    @GetMapping
    public ResponseEntity<List<Jedinka>> getAll() {
        return ResponseEntity.ok(jedinkaService.findAll());
    }

    // GET BY ID – jedna jedinka
    @GetMapping("/{id}")
    public ResponseEntity<Jedinka> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jedinkaService.findById(id));
    }

    // POST – kreiranje nove jedinke
    @PostMapping
    public ResponseEntity<Jedinka> create(@RequestBody Jedinka jedinka) {
        return ResponseEntity.ok(jedinkaService.create(jedinka));
    }

    // PUT – update postojeće jedinke
    @PutMapping("/{id}")
    public ResponseEntity<Jedinka> update(
            @PathVariable Long id,
            @RequestBody Jedinka jedinka) {
        return ResponseEntity.ok(jedinkaService.update(id, jedinka));
    }

    // DELETE – brisanje jedinke
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jedinkaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
