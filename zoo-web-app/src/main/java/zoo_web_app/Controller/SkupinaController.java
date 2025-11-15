package zoo_web_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Skupina;
import zoo_web_app.Repository.SkupinaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skupine")
@CrossOrigin(origins = "*")
public class SkupinaController {

    @Autowired
    private SkupinaRepository skupinaRepo;

    @GetMapping
    public List<Skupina> getAll() {
        return skupinaRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Skupina> getById(@PathVariable Long id) {
        return skupinaRepo.findById(id);
    }

    @PostMapping
    public Skupina add(@RequestBody Skupina skupina) {
        return skupinaRepo.save(skupina);
    }

    @PutMapping("/{id}")
    public Skupina update(@PathVariable Long id, @RequestBody Skupina novaSkupina) {
        return skupinaRepo.findById(id)
                .map(s -> {
                    s.setNaziv(novaSkupina.getNaziv());
                    s.setOpis(novaSkupina.getOpis());
                    return skupinaRepo.save(s);
                })
                .orElseThrow(() -> new RuntimeException("Skupina s ID " + id + " nije pronađena."));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        skupinaRepo.deleteById(id);
        return "Skupina uspješno obrisana.";
    }
}
