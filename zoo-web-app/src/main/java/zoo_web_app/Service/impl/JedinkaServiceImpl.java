package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Jedinka;
import zoo_web_app.Repository.JedinkaRepository;
import zoo_web_app.Service.JedinkaService;
import zoo_web_app.Exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class JedinkaServiceImpl implements JedinkaService {

    private final JedinkaRepository jedinkaRepository;

    public JedinkaServiceImpl(JedinkaRepository jedinkaRepository) {
        this.jedinkaRepository = jedinkaRepository;
    }

    @Override
    public List<Jedinka> findAll() {
        return jedinkaRepository.findAll();
    }

    @Override
    public Jedinka findById(Long id) {
        return jedinkaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jedinka nije pronađena: " + id));
    }

    @Override
    public Jedinka create(Jedinka jedinka) {
        return jedinkaRepository.save(jedinka);
    }

    @Override
    public Jedinka update(Long id, Jedinka updated) {
        Jedinka j = findById(id);

        j.setNadimak(updated.getNadimak());
        j.setIdentifikator(updated.getIdentifikator());
        j.setLatinskiNaziv(updated.getLatinskiNaziv());
        j.setHrvatskiNaziv(updated.getHrvatskiNaziv());
        j.setNastamba(updated.getNastamba());
        j.setNacinNabave(updated.getNacinNabave());
        j.setLink(updated.getLink());
        j.setAktivna(updated.isAktivna());
        j.setDatumNabave(updated.getDatumNabave());
        j.setDatumNeaktivnosti(updated.getDatumNeaktivnosti());
        j.setRazlogNeaktivnosti(updated.getRazlogNeaktivnosti());

        return jedinkaRepository.save(j);
    }

    @Override
    public List<Jedinka> findAllAktivne() {
        return jedinkaRepository.findAllAktivne();
    }

    @Override
    public void delete(Long id) {
        Jedinka j = jedinkaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jedinka ne postoji: " + id));

        j.setAktivna(false);
        j.setDatumNeaktivnosti(LocalDate.now());
        j.setRazlogNeaktivnosti("Uklonjena");

        jedinkaRepository.save(j);
    }

    @Override
    public long brojJedinki() {
        return jedinkaRepository.countByAktivnaTrue();
    }
}
