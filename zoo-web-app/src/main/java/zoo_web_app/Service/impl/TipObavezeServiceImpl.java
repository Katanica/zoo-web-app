package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Obaveza;
import zoo_web_app.Entity.TipObaveze;
import zoo_web_app.Repository.TipObavezeRepository;
import zoo_web_app.Service.TipObavezeService;

import java.util.List;

@Service
public class TipObavezeServiceImpl implements TipObavezeService {

    private final TipObavezeRepository repository;

    public TipObavezeServiceImpl(TipObavezeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipObaveze> findAll() {
        return repository.findAll();
    }

    @Override
    public TipObaveze findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obaveza nije pronađena: " + id));
    }

    @Override
    public TipObaveze create(TipObaveze tipObaveze) {
        return repository.save(tipObaveze);
    }

    @Override
    public TipObaveze update(Long id, TipObaveze tipObaveze) {
        TipObaveze to = findById(id);

        to.setOpisObveze(tipObaveze.getOpisObveze());
        to.setObaveze(tipObaveze.getObaveze());

        return repository.save(to);
    }

    @Override
    public void delete(Long id) {
        TipObaveze to = findById(id);
        repository.delete(to);
    }
}
