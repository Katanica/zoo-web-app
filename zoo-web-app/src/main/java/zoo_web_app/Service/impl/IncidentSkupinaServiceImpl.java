package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.IncidentSkupina;
import zoo_web_app.Repository.IncidentSkupinaRepository;
import zoo_web_app.Service.IncidentSkupinaService;

import java.util.List;

@Service
public class IncidentSkupinaServiceImpl implements IncidentSkupinaService {

    private final IncidentSkupinaRepository repository;

    public IncidentSkupinaServiceImpl(IncidentSkupinaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<IncidentSkupina> findAll() {
        return repository.findAll();
    }

    @Override
    public IncidentSkupina findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("IncidentSkupina nije pronađena: " + id));
    }

    @Override
    public IncidentSkupina create(IncidentSkupina incidentSkupina) {
        return repository.save(incidentSkupina);
    }

    @Override
    public IncidentSkupina update(Long id, IncidentSkupina updated) {
        IncidentSkupina is = findById(id);

        is.setIncident(updated.getIncident());
        is.setSkupina(updated.getSkupina());
        is.setRazinaStete(updated.getRazinaStete());

        return repository.save(is);
    }

    @Override
    public void delete(Long id) {
        IncidentSkupina is = findById(id);
        repository.delete(is);
    }
}