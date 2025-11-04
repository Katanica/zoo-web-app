package zoo_web_app.Zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/zoo") // ????????
@CrossOrigin(origins = "*") // potrebno samo ako front i backend nisu na istoj adresi lokaciji (npr. 8080 i 3000)
public class ZooController {

    @Autowired
    public ZooRepository zooRepository;

    @GetMapping // Optional - jer moze biti zapisa ili ne
    public Optional<Zoo> getZoo(){
        return zooRepository.findAll().stream().findFirst(); // moramo koristiti jer ocekuje Optional
    }

    @PostMapping
    public Zoo saveOrUpdateZoo(@RequestBody Zoo zoo){
        if(zooRepository.count() == 0)
            return zooRepository.save(zoo);
        else{
            System.out.println(zoo);
            Zoo existing = zooRepository.findAll().get(0);
            existing.setNaziv(zoo.getNaziv());
            existing.setAdresa(zoo.getAdresa());
            existing.setTelefon(zoo.getTelefon());
            existing.setMail(zoo.getMail());
            return zooRepository.save(existing);
        }
    }
}
