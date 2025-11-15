package zoo_web_app.Service;

import zoo_web_app.Entity.Skupina;
import java.util.List;

public interface SkupinaService{

    List<Skupina> findAll();

    Skupina findById(Long id);

    Skupina create(Skupina skupina);

    Skupina update(Long id, Skupina skupina);

    void delete(Long id);
}
