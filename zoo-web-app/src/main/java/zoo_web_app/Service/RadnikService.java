package zoo_web_app.Service;

import zoo_web_app.DTO.RadnikCreate;
import zoo_web_app.Entity.Radnik;

import java.util.List;


public interface RadnikService {
    List<Radnik> findAll();

    Radnik findById(Long id);

    Radnik create(Radnik radnik);

    Radnik update(Long id, Radnik radnik);

    void delete(Long id);

    long brojRadnika();

    List<Radnik> findAllWithObrazovanja();

    Radnik createSaObrazovanjima(RadnikCreate dto);
}
