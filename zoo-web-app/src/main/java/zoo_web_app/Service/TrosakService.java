package zoo_web_app.Service;

import zoo_web_app.Entity.Trosak;

import java.util.List;

public interface TrosakService {

    List<Trosak> findAll();

    Trosak findById(Long id);

    Trosak create(Trosak trosak);

    Trosak update(Long id, Trosak trosak);

    void delete(Long id);
}