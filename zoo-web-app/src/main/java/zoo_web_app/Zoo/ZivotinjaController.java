package zoo_web_app.Zoo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/zivotinje")
@CrossOrigin(origins = "*")


public class ZivotinjaController {
    @Autowired
    private ZivotinjaRepository repo;
    @GetMapping
    public List<Zivotinja> getAll() { return repo.findAll(); }
    @GetMapping("/{id}")
    public Optional<Zivotinja> getById(@PathVariable Long id) { return repo.findById(id); }
    @PostMapping
    public Zivotinja create(@RequestBody Zivotinja z) { return repo.save(z); }
    @PutMapping("/{id}")
    public Zivotinja update(@PathVariable Long id, @RequestBody Zivotinja nova) {
        return repo.findById(id).map(z -> {
            z.setIme(nova.getIme());
            z.setLatinskiNaziv(nova.getLatinskiNaziv());
            z.setTip(nova.getTip());
            z.setNacinNabave(nova.getNacinNabave());
            z.setAktivna(nova.isAktivna());
            return repo.save(z);
        }).orElseGet(() -> {
            nova.setId(id);
            return repo.save(nova);
        });
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}


