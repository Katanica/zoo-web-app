package zoo_web_app.Service;

import zoo_web_app.Entity.IncidentJedinka;

import java.util.List;

public interface IncidentJedinkaService {

    List<IncidentJedinka> findAll();

    IncidentJedinka findById(Long id);

    IncidentJedinka create(IncidentJedinka incidentJedinka);

    IncidentJedinka update(Long id, IncidentJedinka incidentJedinka);

    void delete(Long id);
}