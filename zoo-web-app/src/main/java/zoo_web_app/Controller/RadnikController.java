package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.DTO.RadnikCreate;
import zoo_web_app.DTO.RadnikList;
import zoo_web_app.Entity.Radnik;
import zoo_web_app.Service.RadnikService;

import java.util.List;

@RestController
@RequestMapping("/api/radnik")
public class RadnikController {

    private final RadnikService radnikService;

    public RadnikController(RadnikService radnikService) {
        this.radnikService = radnikService;
    }

    @GetMapping("/broj")
    public long brojRadnika(){
        return radnikService.brojRadnika();
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Radnik>> getAll() {
        return ResponseEntity.ok(radnikService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Radnik> getById(@PathVariable Long id) {
        return ResponseEntity.ok(radnikService.findById(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Radnik> create(@RequestBody Radnik radnik) {
        return ResponseEntity.ok(radnikService.create(radnik));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Radnik> update(
            @PathVariable Long id,
            @RequestBody Radnik radnik
    ) {
        return ResponseEntity.ok(radnikService.update(id, radnik));
    }

    // DELETE /api/radnik ovako mi je endpoint za radnik controller
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println("DELETE radnik id = " + id);
        radnikService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/kreiraj-sa-obrazovanjima")
    public ResponseEntity<Radnik> createSaObrazovanjima(@RequestBody RadnikCreate dto) {
        return ResponseEntity.ok(radnikService.createSaObrazovanjima(dto));
    }

    @GetMapping("/sa-obrazovanjima")
    public ResponseEntity<List<RadnikList>> getAllSaObrazovanjima() {

        List<RadnikList> dto = radnikService.findAllWithObrazovanja().stream()
                .map(r -> {

                    String obrazovanjeText = "-";

                    if (r.getObrazovanja() != null && !r.getObrazovanja().isEmpty()) {
                        obrazovanjeText = r.getObrazovanja().stream()
                                .map(ro -> {
                                    String naziv = (ro.getObrazovanje() != null) ? ro.getObrazovanje().getNaziv() : "-";

                                    String od = (ro.getDatumStjecanja() != null) ? ro.getDatumStjecanja().toString() : "";
                                    String dO = (ro.getDatumIsteka() != null) ? ro.getDatumIsteka().toString() : "";

                                    String datumi = (!od.isBlank() || !dO.isBlank()) ? (" (" + od + "–" + dO + ")") : "";
                                    return naziv + datumi;
                                })
                                .distinct()
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("-");
                    }

                    return new RadnikList(
                            r.getId(),
                            r.getIme(),
                            r.getPrezime(),
                            r.getEmail(),
                            r.getTelefon(),
                            r.getStatus(),
                            obrazovanjeText
                    );
                })
                .toList();

        return ResponseEntity.ok(dto);
    }
}
