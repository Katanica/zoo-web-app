package zoo_web_app.Service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import zoo_web_app.DTO.ObavezaFrontend;
import zoo_web_app.Entity.*;
import zoo_web_app.Repository.JedinkaRepository;
import zoo_web_app.Repository.ObavezaRepository;
import zoo_web_app.Repository.RadnikRepository;
import zoo_web_app.Repository.SkupinaRepository;
import zoo_web_app.Service.ObavezaService;
import zoo_web_app.Exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static zoo_web_app.Entity.StatusObaveze.PLANIRANO;

@Service
public class ObavezaServiceImpl implements ObavezaService {

    private final ObavezaRepository obavezaRepository;
    private final RadnikRepository radnikRepository;
    private final JedinkaRepository jedinkaRepository;
    private final SkupinaRepository skupinaRepository;

    public ObavezaServiceImpl(
            ObavezaRepository obavezaRepository,
            RadnikRepository radnikRepository,
            JedinkaRepository jedinkaRepository,
            SkupinaRepository skupinaRepository) {
        this.obavezaRepository = obavezaRepository;
        this.radnikRepository = radnikRepository;
        this.jedinkaRepository = jedinkaRepository;
        this.skupinaRepository = skupinaRepository;
        }

    @Override
    public List<Obaveza> findAll() {
        return obavezaRepository.findAll();
    }

    @Override
    public Obaveza findById(Long id) {
        return obavezaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Obaveza ne postoji: " + id));
    }

    @Override
    public long brojObaveza() {
        return obavezaRepository.count();
    }

    @Override
    public Obaveza create(ObavezaFrontend obaveza) {
        Obaveza o = new Obaveza();

        o.setTip(obaveza.getTip());
        o.setStatus(PLANIRANO);
        o.setRepeatType(obaveza.getRepeatType());
        o.setRepeatEvery(obaveza.getRepeatEvery());
        o.setKomentar(obaveza.getKomentar());

        if(obaveza.getRadnikId() != null){
            Radnik r = radnikRepository.findById(obaveza.getRadnikId())
                    .orElseThrow(() -> new ResourceNotFoundException("Radnik ne postoji!"));
            o.setRadnik(r);
        }
        else
            o.setRadnik(null);

        if(obaveza.getIdJedinke() != null){
            Jedinka j = jedinkaRepository.findById(obaveza.getIdJedinke())
                    .orElseThrow(() -> new ResourceNotFoundException("Jedinka ne postoji!"));
            o.setJedinka(j);
            o.setSkupina(null);
        }
        else if(obaveza.getIdSkupine() != null){
            Skupina s = skupinaRepository.findById(obaveza.getIdSkupine())
                    .orElseThrow(() -> new ResourceNotFoundException("Skupina ne postoji!"));
            o.setSkupina(s);
            o.setJedinka(null);
        }

        if (obaveza.getVrijemeOd() == null) {
            throw new IllegalArgumentException("VrijemeOd je obavezno.");
        }
        if (obaveza.getTrajanje() == null) {
            throw new IllegalArgumentException("Trajanje je obavezno.");
        }
        if (obaveza.getTrajanje() <= 0) {
            throw new IllegalArgumentException("Trajanje mora biti veće od 0.");
        }

        if (obaveza.getVrijemeOd().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Vrijeme početka obaveze ne može biti u prošlosti!");
        }

        o.setVrijemeOd(obaveza.getVrijemeOd());
        o.setVrijemeDo(obaveza.getVrijemeOd().plusMinutes(obaveza.getTrajanje()));


        return obavezaRepository.save(o);
    }

    @Override
    public Obaveza update(Long id, Obaveza updated) {
        return null;
    }

    @Override
    public void delete(Long id) {
        if (!obavezaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Obaveza s ID " + id + " ne postoji");
        }
        obavezaRepository.deleteById(id);
    }

    @Override
    public Obaveza zavrsi(Long id){
        Obaveza o = obavezaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Obaveza s ID " + id + " ne postoji!"));
        o.setStatus(StatusObaveze.OBAVLJENO);
        return obavezaRepository.save(o);
    }

    @Override
    public List<Obaveza> getAktivne() {
        return obavezaRepository.findByStatus(StatusObaveze.PLANIRANO);
    }

    @Override
    public List<Obaveza> getProsle() {
        return obavezaRepository.findByStatus(StatusObaveze.OBAVLJENO);
    }

}
