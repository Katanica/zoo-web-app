package zoo_web_app.Service;


import zoo_web_app.Entity.Karakteristika;

import java.util.List;

public interface KarakteristikaService {
    List<Karakteristika> findAll();

    Karakteristika findById(long id);

    Karakteristika create(Karakteristika karakteristika);

    Karakteristika update(Long id, Karakteristika karakteristika);

    void delete(Long id);
}
