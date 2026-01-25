package zoo_web_app.Service;

import zoo_web_app.Entity.Obrazovanje;

import java.util.List;
import java.util.Optional;

public interface ObrazovanjeService {

    List<Obrazovanje> findAll();

    Obrazovanje findById(Long id);

    Obrazovanje create(Obrazovanje obrazovanje);

    Obrazovanje update(Long id, Obrazovanje obrazovanje);

    void delete(Long id);

    Optional<Obrazovanje> findByNaziv(String naziv);
}