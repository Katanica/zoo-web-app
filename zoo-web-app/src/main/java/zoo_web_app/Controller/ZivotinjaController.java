package zoo_web_app.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Zivotinja> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Zivotinja> getById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping
    public Zivotinja create(@RequestBody Zivotinja z) {
        return repo.save(z);
    }

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
    @GetMapping("/aktivne")
    public List<Zivotinja> getActive() {
        return repo.findByAktivnaTrue();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        return repo.findById(id).map(zivotinja -> {
            zivotinja.setAktivna(false);
            repo.save(zivotinja);
            return ResponseEntity.ok("Životinja je uspješno deaktivirana (označena kao neaktivna).");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Životinja nije pronađena."));
    }
}



