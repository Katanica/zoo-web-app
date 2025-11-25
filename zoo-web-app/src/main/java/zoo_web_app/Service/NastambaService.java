package zoo_web_app.Service;

import zoo_web_app.Entity.Nastamba;

import java.util.List;

public interface NastambaService {

    List<Nastamba> findAll();

    Nastamba findById(Long id);

    Nastamba create(Nastamba nastamba);

    Nastamba update(Long id, Nastamba nastamba);

    void delete(Long id);

    long brojNastambi();
}
