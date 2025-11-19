package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Obrazovanje;
import zoo_web_app.Repository.ObrazovanjeRepository;
import zoo_web_app.Service.ObrazovanjeService;

import java.util.List;

@Service
public class ObrazovanjeServiceImpl implements ObrazovanjeService {

    private final ObrazovanjeRepository obrazovanjeRepository;

    public ObrazovanjeServiceImpl(ObrazovanjeRepository obrazovanjeRepository) {
        this.obrazovanjeRepository = obrazovanjeRepository;
    }

    @Override
    public List<Obrazovanje> findAll() {
        return obrazovanjeRepository.findAll();
    }

    @Override
    public Obrazovanje findById(Long id) {
        return obrazovanjeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obrazovanje nije pronađeno: " + id));
    }

    @Override
    public Obrazovanje create(Obrazovanje obrazovanje) {
        return obrazovanjeRepository.save(obrazovanje);
    }

    @Override
    public Obrazovanje update(Long id, Obrazovanje updated) {
        Obrazovanje o = findById(id);
        o.setNaziv(updated.getNaziv());

        return obrazovanjeRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        Obrazovanje o = findById(id);
        obrazovanjeRepository.delete(o);
    }
}
