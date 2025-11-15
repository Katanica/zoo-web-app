package zoo_web_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Incident;
import zoo_web_app.Repository.IncidentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/incidenti")
@CrossOrigin(origins = "*")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepo;

    @GetMapping
    public List<Incident> getAll() {
        return incidentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Incident> getById(@PathVariable Long id) {
        return incidentRepo.findById(id);
    }

    @PostMapping
    public Incident add(@RequestBody Incident incident) {
        return incidentRepo.save(incident);
    }

    @PutMapping("/{id}")
    public Incident update(@PathVariable Long id, @RequestBody Incident noviIncident) {
        return incidentRepo.findById(id)
                .map(i -> {
                    i.setOpis(noviIncident.getOpis());
                    i.setDatum(noviIncident.getDatum());
                    i.setVrstaIncidenta(noviIncident.getVrstaIncidenta());
                    return incidentRepo.save(i);
                })
                .orElseThrow(() -> new RuntimeException("Incident s ID " + id + " nije pronađen."));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        incidentRepo.deleteById(id);
        return "Incident uspješno obrisan.";
    }
}
