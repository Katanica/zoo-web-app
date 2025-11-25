package zoo_web_app.Service;

import zoo_web_app.Entity.Obaveza;

import java.util.List;

public interface ObavezaService {
    List<Obaveza> findAll();

    Obaveza findById(Long id);

    Obaveza create(Obaveza obaveza);

    Obaveza update(Long id, Obaveza obaveza);

    void delete(Long id);

    long brojObaveza();
}
