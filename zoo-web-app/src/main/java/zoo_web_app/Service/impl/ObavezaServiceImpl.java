package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Obaveza;
import zoo_web_app.Entity.StatusObaveze;
import zoo_web_app.Repository.ObavezaRepository;
import zoo_web_app.Repository.StatusObavezeRepository;
import zoo_web_app.Service.ObavezaService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ObavezaServiceImpl implements ObavezaService {

    private final ObavezaRepository obavezaRepository;

    private final StatusObavezeRepository statusObavezeRepository;

    public ObavezaServiceImpl(ObavezaRepository obavezaRepository, StatusObavezeRepository statusObavezeRepository) {
        this.obavezaRepository = obavezaRepository;
        this.statusObavezeRepository = statusObavezeRepository;
    }

    @Override
    public List<Obaveza> findAll() {
        return obavezaRepository.findAll();
    }

    @Override
    public Obaveza findById(Long id) {
        return obavezaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" Obaveza ne postoji: " + id));
    }

    @Override
    public long brojObaveza() {
        return obavezaRepository.count();
    }

    // CREATE – sa validacijom konflikta
    @Override
    public Obaveza create(Obaveza obaveza) {

        // provjera konflikta termina
        if (obaveza.getRadnik() != null && obaveza.getDatumOd() != null && obaveza.getDatumDo() != null) {

            List<Obaveza> konflikt = obavezaRepository.provjeriPreklapanje(
                    obaveza.getRadnik().getId(),
                    obaveza.getDatumOd(),
                    obaveza.getDatumDo()
            );

            if (!konflikt.isEmpty()) {
                throw new RuntimeException(" Radnik već ima obavezu u tom periodu!");
            }
        }

        return obavezaRepository.save(obaveza);
    }

    // UPDATE – ponovno radi validaciju pri promjeni termina ili radnika
    @Override
    public Obaveza update(Long id, Obaveza updated) {

        Obaveza o = findById(id);

        // samo ako je setovan radnik i datumi – validacija
        if (updated.getRadnik() != null && updated.getDatumOd() != null && updated.getDatumDo() != null) {
            List<Obaveza> konflikt = obavezaRepository.provjeriPreklapanje(
                    updated.getRadnik().getId(),
                    updated.getDatumOd(),
                    updated.getDatumDo()
            );

            if (!konflikt.isEmpty()) {
                throw new RuntimeException(" Radnik već ima obavezu u tom periodu!");
            }
        }

        // UPDATE polja
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

    // DELETE → soft akcija = postavi status na OTKAZANA, ne briši iz baze
    @Override
    public void delete(Long id) {
        Obaveza o = findById(id);

        StatusObaveze status = statusObavezeRepository
                .findByNazivStatusa("OTKAZANA")
                .orElseThrow(() -> new RuntimeException("Status OTKAZANA ne postoji u bazi!"));

        o.setStatus(status);
        obavezaRepository.save(o);
    }

}
