package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.VrstaIncidenta;
import zoo_web_app.Repository.VrstaIncidentaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/vrstaIncidenta")
public class VrstaIncidentaController {

    private final VrstaIncidentaRepository repo;

    public VrstaIncidentaController(VrstaIncidentaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<VrstaIncidenta>> getAll() {
        return ResponseEntity.ok(repo.findAll());
    }
}
