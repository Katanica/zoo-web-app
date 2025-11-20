package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Infrastruktura;
import zoo_web_app.Repository.InfrastrukturaRepository;
import zoo_web_app.Service.InfrastrukturaService;

import java.util.List;

@Service
public class InfrastrukturaServiceImpl implements InfrastrukturaService {
    private final InfrastrukturaRepository repository;

    public InfrastrukturaServiceImpl(InfrastrukturaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Infrastruktura> findAll() {
        return repository.findAll();
    }

    @Override
    public Infrastruktura findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Infrastruktura nije pronadena: " + id));
    }

    @Override
    public Infrastruktura create(Infrastruktura infrastruktura) {
        return repository.save(infrastruktura);
    }

    @Override
    public Infrastruktura update(Long id, Infrastruktura infrastruktura) {
        Infrastruktura i = findById(id);

        i.setId(id);
        i.setNaziv(infrastruktura.getNaziv());
        i.setKategorija(infrastruktura.getKategorija());
        i.setNastambe(infrastruktura.getNastambe());

        return repository.save(i);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
