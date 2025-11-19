package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Radnik;
import zoo_web_app.Repository.RadnikRepository;
import zoo_web_app.Service.RadnikService;

import java.util.List;


@Service
public class RadnikServiceImpl implements RadnikService {
    private final RadnikRepository radnikRepository;

    public RadnikServiceImpl(RadnikRepository radnikRepository) {
        this.radnikRepository = radnikRepository;
    }

    @Override
    public List<Radnik> findAll() {
        return radnikRepository.findAll();
    }

    @Override
    public Radnik findById(Long id) {
        return radnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Radnik nije pronađen: " + id));
    }

    @Override
    public Radnik create(Radnik radnik) {
        return radnikRepository.save(radnik);
    }

    @Override
    public Radnik update(Long id, Radnik updated) {
        Radnik r = findById(id);

        r.setId(updated.getId());
        r.setIme(updated.getIme());
        r.setPrezime(updated.getPrezime());
        r.setTelefon(updated.getTelefon());
        r.setEmail(updated.getEmail());

        return radnikRepository.save(r);
    }

    @Override
    public void delete(Long id) {
        Radnik r = findById(id);
        radnikRepository.delete(r);
    }

}









