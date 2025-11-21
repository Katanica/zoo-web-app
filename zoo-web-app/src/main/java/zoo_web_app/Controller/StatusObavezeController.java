package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.StatusObaveze;
import zoo_web_app.Service.StatusObavezeService;

import java.util.List;

@RestController
@RequestMapping("/api/statusObaveza")
public class StatusObavezeController {

    private final StatusObavezeService statusObavezeService;

    public StatusObavezeController(StatusObavezeService statusObavezeService) {
        this.statusObavezeService = statusObavezeService;
    }

    @GetMapping
    public ResponseEntity<List<StatusObaveze>> getAll() {
        return ResponseEntity.ok(statusObavezeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusObaveze> getById(@PathVariable Long id) {
        return ResponseEntity.ok(statusObavezeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StatusObaveze> create(@RequestBody StatusObaveze statusObaveze) {
        return ResponseEntity.ok(statusObavezeService.create(statusObaveze));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusObaveze> update(
            @PathVariable Long id,
            @RequestBody StatusObaveze statusObaveze
    ) {
        return ResponseEntity.ok(statusObavezeService.update(id, statusObaveze));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        statusObavezeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
