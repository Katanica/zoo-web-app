package zoo_web_app.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/obaveze")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // ako testiraš iz browsera ili Postmana
public class ObavezaController {

    private final ObavezaService obavezaService;

    // 🔹 GET /obaveze
    @GetMapping
    public List<Obaveza> getAll() {
        return obavezaService.getAll();
    }

    // 🔹 POST /obaveze
    @PostMapping
    public Obaveza create(@RequestBody Obaveza obaveza) {
        return obavezaService.create(obaveza);
    }

    // 🔹 PUT /obaveze/{id}/otkazi
    @PutMapping("/{id}/otkazi")
    public void otkazi(@PathVariable Long id) {
        obavezaService.otkaziObavezu(id);
    }

    // 🔹 PUT /obaveze/{id}/odradi
    @PutMapping("/{id}/odradi")
    public void odradi(@PathVariable Long id) {
        obavezaService.odradiObavezu(id);
    }

    // 🔹 PUT /obaveze/{id}/rasporedi
    @PutMapping("/{id}/rasporedi")
    public Obaveza rasporedi(
            @PathVariable Long id,
            @RequestParam Long zaposlenikId,
            @RequestParam String pocetak,
            @RequestParam String kraj) {

        LocalDateTime start = LocalDateTime.parse(pocetak);
        LocalDateTime end = LocalDateTime.parse(kraj);
        return obavezaService.dodijeliZaposlenikaIVrijeme(id, zaposlenikId, start, end);
    }

    // 🔹 DELETE /obaveze/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        obavezaService.delete(id);
    }
}
