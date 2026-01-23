package zoo_web_app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Radnik;
import zoo_web_app.Repository.RadnikRepository;

import java.util.List;

@RestController
@RequestMapping("/api/vodic")
public class VodicController {

    private final RadnikRepository radnikRepository;

    public VodicController(RadnikRepository radnikRepository) {
        this.radnikRepository = radnikRepository;
    }

    // Najjednostavnije: vrati sve radnike kao moguće vodiče
    @GetMapping
    public ResponseEntity<List<Radnik>> getAllVodici() {
        return ResponseEntity.ok(radnikRepository.findAll());
    }
}