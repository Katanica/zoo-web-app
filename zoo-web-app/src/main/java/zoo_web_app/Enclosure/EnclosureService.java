package zoo_web_app.Enclosure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnclosureService {
    public final EnclosureRepository enclosureRepository;

    @Autowired
    public EnclosureService(EnclosureRepository enclosureRepository){
        this.enclosureRepository = enclosureRepository;
    }

    public Enclosure addEnclosure(Enclosure enclosure){
        return enclosureRepository.save(enclosure);
    }
    public List<Enclosure> getAllEnclosures(){
        return enclosureRepository.findAll();
    }
    public void deleteEnclosureByLabel(String label){
        enclosureRepository.deleteById(label);
    }
}
