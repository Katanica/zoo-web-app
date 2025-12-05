package zoo_web_app.Service;

import zoo_web_app.Entity.RadNaIncidentu;
import java.util.List;

public interface RadNaIncidentuService {

    RadNaIncidentu create(RadNaIncidentu rad);
    RadNaIncidentu update(Long id, RadNaIncidentu rad);
    void delete(Long id);

    RadNaIncidentu findById(Long id);
    List<RadNaIncidentu> findAll();
    List<RadNaIncidentu> findByIncident(Long incidentId);
}
