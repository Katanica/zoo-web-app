package zoo_web_app.Service;

import zoo_web_app.Entity.IncidentSkupina;

import java.util.List;

public interface IncidentSkupinaService {

    List<IncidentSkupina> findAll();

    IncidentSkupina findById(Long id);

    IncidentSkupina create(IncidentSkupina incidentSkupina);

    IncidentSkupina update(Long id, IncidentSkupina incidentSkupina);

    void delete(Long id);
}