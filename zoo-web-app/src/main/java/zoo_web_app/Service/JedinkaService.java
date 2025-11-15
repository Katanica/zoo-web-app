package zoo_web_app.Service;

import zoo_web_app.Entity.Jedinka;

import java.util.List;

public interface JedinkaService {

    List<Jedinka> findAll();

    Jedinka findById(Long id);

    Jedinka create(Jedinka jedinka);

    Jedinka update(Long id, Jedinka jedinka);

    void delete(Long id);
}