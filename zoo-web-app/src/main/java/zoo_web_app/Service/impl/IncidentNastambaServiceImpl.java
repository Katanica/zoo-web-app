package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.IncidentNastamba;
import zoo_web_app.Repository.IncidentNastambaRepository;
import zoo_web_app.Service.IncidentNastambaService;

import java.util.List;

@Service
public class IncidentNastambaServiceImpl implements IncidentNastambaService {
    private final IncidentNastambaRepository repository;

    public IncidentNastambaServiceImpl(IncidentNastambaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<IncidentNastamba> findAll() {
        return repository.findAll();
    }

    @Override
    public IncidentNastamba findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("IncidentNastamba nije pronađen: " + id));
    }

    @Override
    public IncidentNastamba create(IncidentNastamba incidentNastamba) {
        return repository.save(incidentNastamba);
    }

    @Override
    public IncidentNastamba update(Long id, IncidentNastamba updated) {
        IncidentNastamba in = findById(id);

        in.setIncident(updated.getIncident());
        in.setNastamba(updated.getNastamba());

        return repository.save(in);
    }

    @Override
    public void delete(Long id) {
        IncidentNastamba in = findById(id);
        repository.delete(in);
    }
}