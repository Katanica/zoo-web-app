package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.IncidentJedinka;
import zoo_web_app.Repository.IncidentJedinkaRepository;
import zoo_web_app.Service.IncidentJedinkaService;

import java.util.List;

@Service
public class IncidentJedinkaServiceImpl implements IncidentJedinkaService {

    private final IncidentJedinkaRepository repository;

    public IncidentJedinkaServiceImpl(IncidentJedinkaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<IncidentJedinka> findAll() {
        return repository.findAll();
    }

    @Override
    public IncidentJedinka findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("IncidentJedinka nije pronađena: " + id));
    }

    @Override
    public IncidentJedinka create(IncidentJedinka incidentJedinka) {
        return repository.save(incidentJedinka);
    }

    @Override
    public IncidentJedinka update(Long id, IncidentJedinka updated) {
        IncidentJedinka ij = findById(id);

        ij.setIncident(updated.getIncident());
        ij.setJedinka(updated.getJedinka());

        return repository.save(ij);
    }

    @Override
    public void delete(Long id) {
        IncidentJedinka ij = findById(id);
        repository.delete(ij);
    }
}
