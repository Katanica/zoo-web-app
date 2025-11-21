package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.VrstaIncidenta;
import zoo_web_app.Repository.VrstaIncidentaRepository;
import zoo_web_app.Service.VrstaIncidentaService;

import java.util.List;

@Service
public class VrstaIncidentaServiceImpl implements VrstaIncidentaService {

    private final VrstaIncidentaRepository repository;

    public VrstaIncidentaServiceImpl(VrstaIncidentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VrstaIncidenta> findAll() {
        return repository.findAll();
    }

    @Override
    public VrstaIncidenta findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("VrstaIncidenta nije pronađena: " + id));
    }

    @Override
    public VrstaIncidenta create(VrstaIncidenta vrstaIncidenta) {
        return repository.save(vrstaIncidenta);
    }

    @Override
    public VrstaIncidenta update(Long id, VrstaIncidenta updated) {
        VrstaIncidenta v = findById(id);

        v.setNaziv(updated.getNaziv());
        v.setIncidenti(updated.getIncidenti());

        return repository.save(v);
    }

    @Override
    public void delete(Long id) {
        VrstaIncidenta vi = findById(id);
        repository.delete(vi);
    }
}
