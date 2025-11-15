package zoo_web_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.VrstaZivotinje;
import zoo_web_app.Repository.VrstaZivotinjeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vrste")
@CrossOrigin(origins = "*")
public class VrstaZivotinjeController {

    @Autowired
    private VrstaZivotinjeRepository vrstaRepo;

    @GetMapping
    public List<VrstaZivotinje> getAll() {
        return vrstaRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<VrstaZivotinje> getById(@PathVariable Long id) {
        return vrstaRepo.findById(id);
    }

    @PostMapping
    public VrstaZivotinje add(@RequestBody VrstaZivotinje vrsta) {
        return vrstaRepo.save(vrsta);
    }

    @PutMapping("/{id}")
    public VrstaZivotinje update(@PathVariable Long id, @RequestBody VrstaZivotinje novaVrsta) {
        return vrstaRepo.findById(id)
                .map(v -> {
                    v.setNaziv(novaVrsta.getNaziv());
                    v.setOpis(novaVrsta.getOpis());
                    return vrstaRepo.save(v);
                })
                .orElseThrow(() -> new RuntimeException("Vrsta sa ID " + id + " nije pronađena."));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        vrstaRepo.deleteById(id);
        return "Vrsta uspješno obrisana.";
    }
}
