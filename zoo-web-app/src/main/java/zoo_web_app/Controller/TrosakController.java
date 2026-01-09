package zoo_web_app.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Trosak;
import zoo_web_app.Repository.TrosakRepository;
import zoo_web_app.Service.TrosakService;

import java.util.List;

@RestController
@RequestMapping("/api/troskovi")
public class TrosakController {
    private final TrosakService trosakService;



    public TrosakController(TrosakService trosakService) {
        this.trosakService = trosakService;


    }

    @GetMapping
    public ResponseEntity<List<Trosak>> getAll() {
        return ResponseEntity.ok(trosakService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trosak> getById(@PathVariable Long id) {
        return ResponseEntity.ok(trosakService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Trosak> create(@RequestBody Trosak trosak) {
        return ResponseEntity.ok(trosakService.create(trosak));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trosak> update(@PathVariable Long id, @RequestBody Trosak trosak) {
        return ResponseEntity.ok(trosakService.update(id, trosak));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trosakService.delete(id);
        return ResponseEntity.noContent().build();
    }



}