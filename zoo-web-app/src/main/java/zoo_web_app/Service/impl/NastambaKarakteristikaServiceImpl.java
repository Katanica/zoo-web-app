package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.NastambaKarakteristika;
import zoo_web_app.Repository.NastambaKarakteristikaRepository;
import zoo_web_app.Service.NastambaKarakteristikaService;

import java.util.List;

@Service
public class NastambaKarakteristikaServiceImpl implements NastambaKarakteristikaService {
    private final NastambaKarakteristikaRepository repository;

    public NastambaKarakteristikaServiceImpl(NastambaKarakteristikaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NastambaKarakteristika> findAll() {
        return repository.findAll();
    }

    @Override
    public NastambaKarakteristika findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("NastambaKarakteristika nije pronađena: " + id));
    }

    @Override
    public NastambaKarakteristika create(NastambaKarakteristika nastambaKarakteristika) {
        return repository.save(nastambaKarakteristika);
    }

    @Override
    public NastambaKarakteristika update(Long id, NastambaKarakteristika nastambaKarakteristika) {
        NastambaKarakteristika nk = findById(id);

        nk.setNastamba(nastambaKarakteristika.getNastamba());
        nk.setKarakteristika(nastambaKarakteristika.getKarakteristika());

        return repository.save(nk);
    }

    @Override
    public void delete(Long id) {
        NastambaKarakteristika nk = findById(id);
        repository.delete(nk);
    }
}
