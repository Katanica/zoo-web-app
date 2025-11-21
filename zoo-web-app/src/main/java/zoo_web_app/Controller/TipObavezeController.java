package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.TipObaveze;
import zoo_web_app.Service.TipObavezeService;

import java.util.List;

@RestController
@RequestMapping("/tipObaveza")
public class TipObavezeController {

    private final TipObavezeService tipObavezeService;

    public TipObavezeController(TipObavezeService tipObavezeService) {
        this.tipObavezeService = tipObavezeService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<TipObaveze>> getAll() {
        return ResponseEntity.ok(tipObavezeService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TipObaveze> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tipObavezeService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TipObaveze> create(@RequestBody TipObaveze tipObaveza) {
        return ResponseEntity.ok(tipObavezeService.create(tipObaveza));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TipObaveze> update(
            @PathVariable Long id,
            @RequestBody TipObaveze tipObaveza
    ) {
        return ResponseEntity.ok(tipObavezeService.update(id, tipObaveza));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipObavezeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
