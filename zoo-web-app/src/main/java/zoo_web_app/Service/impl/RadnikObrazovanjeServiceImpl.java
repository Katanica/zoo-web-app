package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.RadnikObrazovanje;
import zoo_web_app.Repository.RadnikObrazovanjeRepository;
import zoo_web_app.Service.RadnikObrazovanjeService;

import java.util.List;

@Service
public class RadnikObrazovanjeServiceImpl implements RadnikObrazovanjeService {

    private final RadnikObrazovanjeRepository repository;

    public RadnikObrazovanjeServiceImpl(RadnikObrazovanjeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RadnikObrazovanje> findAll() {
        return repository.findAll();
    }

    @Override
    public RadnikObrazovanje findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("RadnikObrazovanje nije pronađeno: " + id));
    }

    @Override
    public RadnikObrazovanje create(RadnikObrazovanje ro) {
        return repository.save(ro);
    }

    @Override
    public RadnikObrazovanje update(Long id, RadnikObrazovanje updated) {
        RadnikObrazovanje ro = findById(id);

        ro.setId(updated.getId());
        ro.setRadnik(updated.getRadnik());
        ro.setObrazovanje(updated.getObrazovanje());
        ro.setDatumStjecanja(updated.getDatumStjecanja());

        return repository.save(ro);
    }

    @Override
    public void delete(Long id) {
        RadnikObrazovanje ro = findById(id);
        repository.delete(ro);
    }
}
