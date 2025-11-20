package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Incident;
import zoo_web_app.Repository.IncidentRepository;
import zoo_web_app.Service.IncidentService;

import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {
    private final IncidentRepository incidentRepository;

    public IncidentServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public List<Incident> findAll() {
        return incidentRepository.findAll();
    }

    @Override
    public Incident findById(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident nije pronađen: " + id));
    }

    @Override
    public Incident create(Incident incident) {
        return incidentRepository.save(incident);
    }

    @Override
    public Incident update(Long id, Incident updated) {
        Incident i = findById(id);

        i.setVrsta(updated.getVrsta());
        i.setDatum(updated.getDatum());
        i.setOpis(updated.getOpis());

        return incidentRepository.save(i);
    }

    @Override
    public void delete(Long id) {
        Incident i = findById(id);
        incidentRepository.delete(i);
    }
}
