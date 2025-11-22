package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Skupina;
import zoo_web_app.Repository.SkupinaRepository;
import zoo_web_app.Service.SkupinaService;

import java.util.List;

@Service
public class SkupinaServiceImpl implements SkupinaService {

    private final SkupinaRepository skupinaRepository;

    public SkupinaServiceImpl(SkupinaRepository skupinaRepository) {

        this.skupinaRepository = skupinaRepository;
    }

    @Override
    public List<Skupina> findAll() {
        return skupinaRepository.findAll();
    }

    @Override
    public Skupina findById(Long id) {
        return skupinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skupina nije pronađena: " + id));
    }

    @Override
    public Skupina create(Skupina skupina) {
        return skupinaRepository.save(skupina);
    }

    @Override
    public Skupina update(Long id, Skupina updated) {
        Skupina s = findById(id);

        s.setIdentifikator(updated.getIdentifikator());
        s.setLatinskiNaziv(updated.getLatinskiNaziv());
        s.setHrvatskiNaziv(updated.getHrvatskiNaziv());
        s.setNastamba(updated.getNastamba());
        s.setNacinNabave(updated.getNacinNabave());
        s.setLink(updated.getLink());
        s.setProcijenjeniBroj(updated.getProcijenjeniBroj());
        s.setDatumNabave(updated.getDatumNabave());
        s.setAktivna(updated.isAktivna());
        s.setRazlogNeaktivnosti(updated.getRazlogNeaktivnosti());
        s.setObaveze(updated.getObaveze());
        s.setTroskovi(updated.getTroskovi());
        s.setIncidenti(updated.getIncidenti());

        return skupinaRepository.save(s);
    }

    @Override
    public void delete(Long id) {
        Skupina s = findById(id);
        skupinaRepository.delete(s);
    }
}