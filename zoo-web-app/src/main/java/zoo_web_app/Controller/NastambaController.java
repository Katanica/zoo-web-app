package zoo_web_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Entity.Nastamba;
import zoo_web_app.Repository.NastambaRepository;

import java.util.List;

@RestController
@RequestMapping("/nastamba")
public class NastambaController {
    @Autowired
    private NastambaRepository nastambaRepository;

    @GetMapping
    public List<Nastamba> getAll(){
        return nastambaRepository.findAll();
    }

    @PostMapping
    public Nastamba add(@RequestBody Nastamba nastamba){
        return nastambaRepository.save(nastamba);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        nastambaRepository.deleteById(id);
    }

}
