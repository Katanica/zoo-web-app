package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.VrstaZivotinje;
import zoo_web_app.Repository.VrstaZivotinjeRepository;
import zoo_web_app.Service.VrstaZivotinjeService;
import zoo_web_app.Exception.ResourceNotFoundException;

import java.util.List;

@Service
public class VrstaZivotinjeServiceImpl implements VrstaZivotinjeService {

    private final VrstaZivotinjeRepository repository;

    public VrstaZivotinjeServiceImpl(VrstaZivotinjeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VrstaZivotinje> findAll() {
        return repository.findAll();
    }

    @Override
    public VrstaZivotinje findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vrsta nije pronađena: " + id));
    }

    @Override
    public VrstaZivotinje create(VrstaZivotinje vrsta) {
        return repository.save(vrsta);
    }

    @Override
    public VrstaZivotinje update(Long id, VrstaZivotinje updated) {
        VrstaZivotinje v = findById(id);

        v.setHrvatskiNaziv(updated.getHrvatskiNaziv());
        v.setLatinskiNaziv(updated.getLatinskiNaziv());
        v.setLink(updated.getLink());

        return repository.save(v);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
