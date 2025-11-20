package zoo_web_app.Service;

import zoo_web_app.Entity.IncidentSkupina;
import zoo_web_app.Entity.Infrastruktura;

import java.util.List;

public interface InfrastrukturaService{
    List<Infrastruktura> findAll();

    Infrastruktura findById(Long id);

    Infrastruktura create(Infrastruktura infrastruktura);

    Infrastruktura update(Long id, Infrastruktura infrastruktura);

    void delete(Long id);
}
