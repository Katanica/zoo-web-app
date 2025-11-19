package zoo_web_app.Service;

import zoo_web_app.Entity.Obrazovanje;

import java.util.List;

public interface ObrazovanjeService {

    List<Obrazovanje> findAll();

    Obrazovanje findById(Long id);

    Obrazovanje create(Obrazovanje obrazovanje);

    Obrazovanje update(Long id, Obrazovanje obrazovanje);

    void delete(Long id);
}