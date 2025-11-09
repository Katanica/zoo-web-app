package zoo_web_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Karakteristika;
import zoo_web_app.Entity.Nastamba;
import zoo_web_app.Entity.Zivotinja;
import zoo_web_app.Repository.KarakteristikaRepository;
import zoo_web_app.Repository.NastambaRepository;
import zoo_web_app.Repository.ZivotinjaRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nastamba")
public class NastambaController {
    @Autowired
    private NastambaRepository nastambaRepository;

    @Autowired
    private KarakteristikaRepository karakteristikaRepository;

    @Autowired
    private ZivotinjaRepository zivotinjaRepository;

    @GetMapping
    public List<Nastamba> getAll(){
        return nastambaRepository.findAll();
    }

    @PostMapping
    public Nastamba add(@RequestBody Map<String, Object> body){
        String oznaka = (String) body.get("oznaka");
        String geometrija = (String) body.get("geometrija");

        List<Long> karaktIds = (List<Long>) body.get("karakteristikeIds");
        List<Long> zivotinjeIds = (List<Long>) body.get("zivotinjeIds");

        Nastamba n = new Nastamba();

        n.setOznaka(oznaka);
        n.setGeometrija(geometrija);

        List<Karakteristika> karakteristike = karaktIds != null ? karakteristikaRepository.findAllById(karaktIds) : List.of();
        n.setKarakteristike(karakteristike);

        List<Zivotinja> zivotinje = zivotinjeIds != null ? zivotinjaRepository.findAllById(zivotinjeIds) : List.of();
        n.setZivotinje(zivotinje);

        System.out.println("ZIVOTINJE" + zivotinje);

        return nastambaRepository.save(n);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        nastambaRepository.deleteById(id);
    }

}
