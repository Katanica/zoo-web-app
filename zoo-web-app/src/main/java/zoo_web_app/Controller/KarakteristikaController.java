package zoo_web_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Karakteristika;
import zoo_web_app.Repository.KarakteristikaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/karakteristike")
public class KarakteristikaController {
    @Autowired
    private KarakteristikaRepository karakteristikaRepository;

    @PostMapping
    public Karakteristika add(@RequestBody Karakteristika karakteristika){
        System.out.println(karakteristika);
        return karakteristikaRepository.save(karakteristika);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        karakteristikaRepository.deleteById(id);
    }

    @GetMapping
    public List<Karakteristika> getAll(){
        return karakteristikaRepository.findAll();
    }
}
