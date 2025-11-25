package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Nastamba;
import zoo_web_app.Repository.NastambaRepository;
import zoo_web_app.Service.NastambaService;

import java.util.List;

@Service
public class NastambaServiceImpl implements NastambaService {
    private final NastambaRepository nastambaRepository;


    public NastambaServiceImpl(NastambaRepository nastambaRepository) {
        this.nastambaRepository = nastambaRepository;

    }

    @Override
    public long brojNastambi(){
        return  nastambaRepository.count();
    }

    @Override
    public List<Nastamba> findAll() {
        return nastambaRepository.findAll();
    }

    @Override
    public Nastamba findById(Long id) {
        return nastambaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nastamba nije pronađena: " + id));
    }

    @Override
    public Nastamba create(Nastamba nastamba) {
        return nastambaRepository.save(nastamba);
    }

    @Override
    public Nastamba update(Long id, Nastamba updated) {
        Nastamba n = findById(id);

        n.setOznaka(updated.getOznaka());
        n.setOpis(updated.getOpis());
        n.setGeometrija(updated.getGeometrija());

        return nastambaRepository.save(n);
    }

    @Override
    public void delete(Long id) {
        Nastamba n = findById(id);
        nastambaRepository.delete(n);
    }
}
