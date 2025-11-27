package zoo_web_app.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Jedinka;
import zoo_web_app.Repository.JedinkaRepository;
import zoo_web_app.Service.JedinkaService;
import zoo_web_app.Service.impl.JedinkaServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/jedinke")
public class JedinkaController {

    private final JedinkaServiceImpl jedinkaServiceImpl;

    private final JedinkaService jedinkaService;

    private final JedinkaRepository jedinkaRepository;
    @GetMapping("/broj")
    public long brojJedinki(){
        return jedinkaService.brojJedinki();
    }

    public JedinkaController(JedinkaServiceImpl jedinkaServiceImpl, JedinkaService jedinkaService, JedinkaRepository jedinkaRepository) {
        this.jedinkaServiceImpl = jedinkaServiceImpl;
        this.jedinkaService = jedinkaService;
        this.jedinkaRepository = jedinkaRepository;
    }

    // GET ALL – vrati sve jedinke
    @GetMapping
    public ResponseEntity<List<Jedinka>> getAll() {
        return ResponseEntity.ok(jedinkaServiceImpl.findAll());
    }

    // GET BY ID – jedna jedinka
    @GetMapping("/{id}")
    public ResponseEntity<Jedinka> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jedinkaServiceImpl.findById(id));
    }

    // POST – kreiranje nove jedinke
    @PostMapping
    public ResponseEntity<Jedinka> create(@RequestBody Jedinka jedinka) {
        return ResponseEntity.ok(jedinkaServiceImpl.create(jedinka));
    }

    // PUT – update postojeće jedinke
    @PutMapping("/{id}")
    public ResponseEntity<Jedinka> update(
            @PathVariable Long id,
            @RequestBody Jedinka jedinka) {
        return ResponseEntity.ok(jedinkaServiceImpl.update(id, jedinka));
    }
    @GetMapping("/aktivne")
    public List<Jedinka> aktivneJedinke(){
        return jedinkaRepository.findAllAktivne();
    }

    // DELETE – brisanje jedinke
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jedinkaServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
