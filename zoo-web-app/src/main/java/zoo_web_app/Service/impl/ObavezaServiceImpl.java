package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Obaveza;
import zoo_web_app.Entity.StatusObaveze;
import zoo_web_app.Repository.ObavezaRepository;
import zoo_web_app.Repository.StatusObavezeRepository;
import zoo_web_app.Service.ObavezaService;
import zoo_web_app.Exception.ResourceNotFoundException;

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
                .orElseThrow(() -> new ResourceNotFoundException("Obaveza ne postoji: " + id));
    }

    @Override
    public long brojObaveza() {
        return obavezaRepository.count();
    }

    @Override
    public Obaveza create(Obaveza obaveza) {

        if (obaveza.getRadnik() != null && obaveza.getDatumOd() != null && obaveza.getDatumDo() != null) {

            List<Obaveza> konflikt = obavezaRepository.provjeriPreklapanje(
                    obaveza.getRadnik().getId(),
                    obaveza.getDatumOd(),
                    obaveza.getDatumDo()
            );

            if (!konflikt.isEmpty()) {
                throw new ResourceNotFoundException("Radnik već ima obavezu u tom periodu!");
            }
        }

        provjeriLicencu(obaveza);
        provjeriIstekLicence(obaveza);

        return obavezaRepository.save(obaveza);
    }

    @Override
    public Obaveza update(Long id, Obaveza updated) {

        Obaveza o = findById(id);

        if (updated.getRadnik() != null && updated.getDatumOd() != null && updated.getDatumDo() != null) {

            List<Obaveza> konflikt = obavezaRepository.provjeriPreklapanje(
                    updated.getRadnik().getId(),
                    updated.getDatumOd(),
                    updated.getDatumDo()
            );

            if (!konflikt.isEmpty()) {
                throw new ResourceNotFoundException("Radnik već ima obavezu u tom periodu!");
            }
        }

        o.setTip(updated.getTip());
        o.setStatus(updated.getStatus());
        o.setRadnik(updated.getRadnik());
        o.setJedinka(updated.getJedinka());
        o.setSkupina(updated.getSkupina());
        o.setDatumOd(updated.getDatumOd());
        o.setDatumDo(updated.getDatumDo());
        o.setKomentar(updated.getKomentar());

        provjeriLicencu(o);
        provjeriIstekLicence(o);

        return obavezaRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        Obaveza o = findById(id);

        StatusObaveze status = statusObavezeRepository
                .findByNazivStatusa("OTKAZANA")
                .orElseThrow(() -> new ResourceNotFoundException("Status OTKAZANA ne postoji u bazi!"));

        o.setStatus(status);
        obavezaRepository.save(o);
    }

    private void provjeriLicencu(Obaveza obaveza) {
        if (obaveza.getRadnik() == null) return;
        if (!obaveza.getTip().isZahtjevaLicencu()) return;

        boolean imaLicencu = obaveza.getRadnik()
                .getObrazovanja()
                .stream()
                .anyMatch(o -> o.getObrazovanje().getNaziv().equalsIgnoreCase("VODIC"));

        if (!imaLicencu) {
            throw new ResourceNotFoundException("Radnik nema potrebnu licencu za ovu obavezu.");
        }
    }

    private void provjeriIstekLicence(Obaveza obaveza) {
        if (obaveza.getRadnik() == null) return;
        if (!obaveza.getTip().isZahtjevaLicencu()) return;

        boolean isteklaLicenca = obaveza.getRadnik()
                .getObrazovanja()
                .stream()
                .anyMatch(o ->
                        o.getObrazovanje().getNaziv().equalsIgnoreCase("VODIC")
                                && o.getDatumIsteka() != null
                                && o.getDatumIsteka().isBefore(LocalDate.now())
                );

        if (isteklaLicenca) {
            throw new ResourceNotFoundException("Licenca VODIC je istekla - radnik ne smije voditi turu.");
        }
    }
}
