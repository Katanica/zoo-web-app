package zoo_web_app.Service;

import zoo_web_app.DTO.ObavezaFrontend;
import zoo_web_app.Entity.Obaveza;

import java.util.List;

public interface ObavezaService {
    List<Obaveza> findAll();

    Obaveza findById(Long id);

    Obaveza create(ObavezaFrontend obaveza);

    Obaveza update(Long id, Obaveza obaveza);

    void delete(Long id);

    long brojObaveza();
}
