package zoo_web_app.Service;

import zoo_web_app.Entity.RadnikObrazovanje;

import java.util.List;

public interface RadnikObrazovanjeService {

    List<RadnikObrazovanje> findAll();

    RadnikObrazovanje findById(Long id);

    RadnikObrazovanje create(RadnikObrazovanje radnikObrazovanje);

    RadnikObrazovanje update(Long id, RadnikObrazovanje radnikObrazovanje);

    void delete(Long id);
}
