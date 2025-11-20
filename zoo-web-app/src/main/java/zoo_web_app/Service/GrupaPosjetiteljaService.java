package zoo_web_app.Service;

import zoo_web_app.Entity.GrupaPosjetitelja;

import java.util.List;

public interface GrupaPosjetiteljaService {

    List<GrupaPosjetitelja> findAll();

    GrupaPosjetitelja findById(Long id);

    GrupaPosjetitelja create(GrupaPosjetitelja grupa);

    GrupaPosjetitelja update(Long id, GrupaPosjetitelja grupa);

    void delete(Long id);
}
