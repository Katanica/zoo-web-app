package zoo_web_app.Enclosure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enclosure")
public class EnclosureController {
    private final EnclosureService enclosureService;

    @Autowired
    public EnclosureController(EnclosureService enclosureService) {
        this.enclosureService = enclosureService;
    }
    @PostMapping
    public Enclosure addEnclosure(@ RequestBody Enclosure enclosure) {
        return enclosureService.addEnclosure(enclosure);
    }
    @GetMapping
    public List<Enclosure> getAllEnclosures(){
        return enclosureService.getAllEnclosures();
    }
    @DeleteMapping("/{label}")
    public void deleteEnclosureByLabel(@PathVariable String label){
        enclosureService.deleteEnclosureByLabel(label);
    }
}
