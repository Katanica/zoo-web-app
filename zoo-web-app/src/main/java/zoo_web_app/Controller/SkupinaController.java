package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Skupina;
import zoo_web_app.Repository.SkupinaRepository;
import zoo_web_app.Service.SkupinaService;

import java.util.List;

@RestController
@RequestMapping("/api/skupine")
public class SkupinaController {

    private final SkupinaService skupinaService;

    private final SkupinaRepository skupinaRepository;

    public SkupinaController(SkupinaService skupinaService,  SkupinaRepository skupinaRepository)  {

        this.skupinaService = skupinaService;
        this.skupinaRepository = skupinaRepository;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Skupina>> getAll() {
        return ResponseEntity.ok(skupinaService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Skupina> getById(@PathVariable Long id) {
        return ResponseEntity.ok(skupinaService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Skupina> create(@RequestBody Skupina skupina) {
        return ResponseEntity.ok(skupinaService.create(skupina));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Skupina> update(
            @PathVariable Long id,
            @RequestBody Skupina skupina
    ) {
        return ResponseEntity.ok(skupinaService.update(id, skupina));
    }

    @GetMapping("/aktivne")
    public List<Skupina> aktivne(){
        return skupinaRepository.findAllAktivne();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skupinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
