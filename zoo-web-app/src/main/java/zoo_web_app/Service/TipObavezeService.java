package zoo_web_app.Service;

import zoo_web_app.Entity.TipObaveze;

import java.util.List;

public interface TipObavezeService{
    List<TipObaveze> findAll();

    TipObaveze findById(Long id);

    TipObaveze create(TipObaveze tipObaveze);

    TipObaveze update(Long id, TipObaveze tipObaveze);

    void delete(Long id);
}
