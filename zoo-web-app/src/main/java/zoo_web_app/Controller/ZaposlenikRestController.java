package zoo_web_app.Controller;

import zoo_web_app.Entity.Zaposlenik;
import zoo_web_app.Service.ZaposlenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/zaposlenici")
@CrossOrigin(origins = "*")
public class ZaposlenikRestController {

    @Autowired
    private ZaposlenikService service;

    @GetMapping
    public List<Zaposlenik> getAll() {
        return service.findAll();
    }

    @GetMapping("/filter")
    public List<Zaposlenik> filter(@RequestParam(required = false) String kvalifikacija) {
        if (kvalifikacija == null || kvalifikacija.trim().isEmpty()) {
            return service.findAll();
        }
        return service.filterByKvalifikacija(kvalifikacija);
    }

    @PostMapping
    public Zaposlenik create(@RequestBody Zaposlenik zaposlenik) {
        return service.save(zaposlenik);
    }

    @PutMapping("/{id}")
    public Zaposlenik update(@PathVariable Long id, @RequestBody Zaposlenik zaposlenik) {
        zaposlenik.setId(id);
        return service.save(zaposlenik);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}