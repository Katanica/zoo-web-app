package zoo_web_app.Service;

import zoo_web_app.Entity.IncidentNastamba;

import java.util.List;

public interface IncidentNastambaService {

    List<IncidentNastamba> findAll();

    IncidentNastamba findById(Long id);

    IncidentNastamba create(IncidentNastamba incidentNastamba);

    IncidentNastamba update(Long id, IncidentNastamba incidentNastamba);

    void delete(Long id);
}
