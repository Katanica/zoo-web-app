package zoo_web_app.Service;

import zoo_web_app.Entity.Zaposlenik;
import zoo_web_app.Repository.ZaposlenikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ZaposlenikService {

    @Autowired
    private ZaposlenikRepository repository;

    public List<Zaposlenik> findAll() {
        return repository.findAll();
    }

    public Zaposlenik save(Zaposlenik zaposlenik) {
        return repository.save(zaposlenik);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Zaposlenik> filterByKvalifikacija(String kvalifikacija) {
        return repository.findByKvalifikacijeContainingIgnoreCase(kvalifikacija);
    }


    }
