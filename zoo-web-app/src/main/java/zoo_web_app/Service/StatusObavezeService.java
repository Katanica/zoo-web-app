package zoo_web_app.Service;

import zoo_web_app.Entity.StatusObaveze;

import java.util.List;

public interface StatusObavezeService {
    List<StatusObaveze> findAll();

    StatusObaveze findById(Long id);

    StatusObaveze create(StatusObaveze statusObaveze);

    StatusObaveze update(Long id, StatusObaveze statusObaveze);

    void delete(Long id);
}
