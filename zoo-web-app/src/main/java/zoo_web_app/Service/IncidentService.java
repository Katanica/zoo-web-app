package zoo_web_app.Service;

import zoo_web_app.Entity.Incident;

import java.util.List;

public interface IncidentService {
    List<Incident> findAll();

    public Incident findById(Long id);

    public Incident create(Incident incident);

    public Incident update(Long id, Incident incident);

    void delete(Long id);
}