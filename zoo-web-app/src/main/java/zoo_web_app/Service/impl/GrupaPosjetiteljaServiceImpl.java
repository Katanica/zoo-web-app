package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.GrupaPosjetitelja;
import zoo_web_app.Repository.GrupaPosjetiteljaRepository;
import zoo_web_app.Service.GrupaPosjetiteljaService;

import java.util.List;

@Service
public class GrupaPosjetiteljaServiceImpl implements GrupaPosjetiteljaService {

    private final GrupaPosjetiteljaRepository repository;

    public GrupaPosjetiteljaServiceImpl(GrupaPosjetiteljaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GrupaPosjetitelja> findAll() {
        return repository.findAll();
    }

    @Override
    public GrupaPosjetitelja findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupa posjetitelja nije pronađena: " + id));
    }

    @Override
    public GrupaPosjetitelja create(GrupaPosjetitelja grupa) {
        return repository.save(grupa);
    }

    @Override
    public GrupaPosjetitelja update(Long id, GrupaPosjetitelja updated) {
        GrupaPosjetitelja g = findById(id);

        g.setNaziv(updated.getNaziv());
        g.setKontaktMail(updated.getKontaktMail());
        g.setBrojOsoba(updated.getBrojOsoba());
        g.setDatumDolaska(updated.getDatumDolaska());
        g.setVrijemePocetka(updated.getVrijemePocetka());
        g.setVrijemeKraja(updated.getVrijemeKraja());
        g.setVodic(updated.getVodic());
        g.setStatus(updated.getStatus());

        return repository.save(g);
    }

    @Override
    public void delete(Long id) {
        GrupaPosjetitelja g = findById(id);
        repository.delete(g);
    }
}
