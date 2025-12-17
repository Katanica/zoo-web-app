package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.NastambaInfrastruktura;
import zoo_web_app.Repository.NastambaInfrastrukturaRepository;
import zoo_web_app.Service.NastambaInfrastrukturaService;
import zoo_web_app.Exception.ResourceNotFoundException;

import java.util.List;

@Service
public class NastambaInfrastrukturaServiceImpl implements NastambaInfrastrukturaService {

    private final NastambaInfrastrukturaRepository repository;

    public NastambaInfrastrukturaServiceImpl(NastambaInfrastrukturaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NastambaInfrastruktura> findAll() {
        return repository.findAll();
    }

    @Override
    public NastambaInfrastruktura findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NastambaInfrastruktura nije pronađena: " + id));
    }

    @Override
    public NastambaInfrastruktura create(NastambaInfrastruktura nastambaInfrastruktura) {
        return repository.save(nastambaInfrastruktura);
    }

    @Override
    public NastambaInfrastruktura update(Long id, NastambaInfrastruktura nastambaInfrastruktura) {
        NastambaInfrastruktura ni = findById(id);

        ni.setNastamba(nastambaInfrastruktura.getNastamba());
        ni.setInfrastruktura(nastambaInfrastruktura.getInfrastruktura());
        ni.setKolicina(nastambaInfrastruktura.getKolicina());

        return repository.save(ni);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
