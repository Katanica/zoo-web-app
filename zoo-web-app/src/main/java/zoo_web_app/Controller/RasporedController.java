package zoo_web_app.Controller;

import org.springframework.web.bind.annotation.*;
import zoo_web_app.DTO.RasporedRasponZahtjev;
import zoo_web_app.Entity.Raspored;
import zoo_web_app.Entity.Smjena;
import zoo_web_app.Entity.VrstaUnosaRasporeda;
import zoo_web_app.Service.RasporedService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/raspored")
@CrossOrigin
public class RasporedController {

    private final RasporedService rasporedService;

    public RasporedController(RasporedService rasporedService) {
        this.rasporedService = rasporedService;
    }

    @GetMapping
    public List<Raspored> dohvatiSveRasporede(){
        return rasporedService.findAll();
    }

    // DOHVAT rasporeda za radnika u tjednu
    @GetMapping("/tjedan")
    public List<Raspored> dohvatiZaTjedan(@RequestParam Long radnikId,
                                          @RequestParam LocalDate ponedjeljak) {

        return rasporedService.dohvatiRasporedZaRadnikaUTjednu(radnikId, ponedjeljak);
    }

    // UPIS jednog dana
    @PostMapping("/dan")
    public void spremiDan(@RequestParam Long radnikId,
                          @RequestParam VrstaUnosaRasporeda vrstaUnosa,
                          @RequestParam(required = false) Smjena smjena,
                          @RequestParam LocalDate datum) {

        rasporedService.spremiJedanDan(radnikId, vrstaUnosa, smjena, datum);
    }

    // UPIS raspona dana
    @PostMapping("/raspon")
    public void spremiRaspon(@RequestBody RasporedRasponZahtjev zahtjev) {

        rasporedService.spremiRaspon(
                zahtjev.radnikId,
                zahtjev.vrstaUnosa,
                zahtjev.smjena,
                zahtjev.datumOd,
                zahtjev.datumDo
        );
    }

    // BRISANJE zapisa
    @DeleteMapping("/{id}")
    public void obrisi(@PathVariable Long id) {
        rasporedService.obrisi(id);
    }
}
