package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Obaveza;
import zoo_web_app.Repository.ObavezaRepository;
import zoo_web_app.Service.ObavezaService;

import java.util.List;

@Service
public class ObavezaServiceImpl implements ObavezaService {
    private final ObavezaRepository obavezaRepository;

    public ObavezaServiceImpl(ObavezaRepository obavezaRepository) {
        this.obavezaRepository = obavezaRepository;
    }

    @Override
    public List<Obaveza> findAll() {
        return obavezaRepository.findAll();
    }

    @Override
    public Obaveza findById(Long id) {
        return obavezaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obaveza nije pronađena: " + id));
    }

    @Override
    public Obaveza create(Obaveza obaveza) {
        return obavezaRepository.save(obaveza);
    }

    @Override
    public Obaveza update(Long id, Obaveza updated) {
        Obaveza o = findById(id);

        o.setTip(updated.getTip());
        o.setStatus(updated.getStatus());
        o.setRadnik(updated.getRadnik());
        o.setJedinka(updated.getJedinka());
        o.setSkupina(updated.getSkupina());
        o.setDatumOd(updated.getDatumOd());
        o.setDatumDo(updated.getDatumDo());
        o.setKomentar(updated.getKomentar());

        return obavezaRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        Obaveza o = findById(id);
        obavezaRepository.delete(o);
    }
}