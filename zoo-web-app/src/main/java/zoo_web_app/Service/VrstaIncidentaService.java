package zoo_web_app.Service;

import zoo_web_app.Entity.VrstaIncidenta;

import java.util.List;

public interface VrstaIncidentaService {
    List<VrstaIncidenta> findAll();

    VrstaIncidenta findById(Long id);

    VrstaIncidenta create(VrstaIncidenta vrsta);

    VrstaIncidenta update(Long id, VrstaIncidenta vrsta);

    void delete(Long id);
}
