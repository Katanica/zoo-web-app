package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo_web_app.Entity.Radnik;
import zoo_web_app.Entity.Raspored;
import zoo_web_app.Entity.Smjena;
import zoo_web_app.Entity.VrstaUnosaRasporeda;
import zoo_web_app.Repository.RadnikRepository;
import zoo_web_app.Repository.RasporedRepository;
import zoo_web_app.Service.RasporedService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RasporedServiceImpl implements RasporedService {

    private final RasporedRepository rasporedRepository;
    private final RadnikRepository radnikRepository;

    public RasporedServiceImpl(RasporedRepository rasporedRepository,
                               RadnikRepository radnikRepository) {
        this.rasporedRepository = rasporedRepository;
        this.radnikRepository = radnikRepository;
    }

    @Override
    public void spremiJedanDan(Long radnikId,
                               VrstaUnosaRasporeda vrstaUnosa,
                               Smjena smjena,
                               LocalDate datum) {

        Radnik radnik = radnikRepository.findById(radnikId)
                .orElseThrow(() -> new IllegalArgumentException("Radnik ne postoji"));

        Raspored raspored = rasporedRepository
                .findByRadnikIdAndDatum(radnikId, datum)
                .orElse(new Raspored());

        raspored.setRadnik(radnik);
        raspored.setDatum(datum);
        raspored.setVrstaUnosa(vrstaUnosa);

        if (vrstaUnosa == VrstaUnosaRasporeda.RAD) {
            raspored.setSmjena(smjena);
        } else {
            raspored.setSmjena(null);
        }

        rasporedRepository.save(raspored);
    }

    @Override
    public void spremiRaspon(Long radnikId,
                             VrstaUnosaRasporeda vrstaUnosa,
                             Smjena smjena,
                             LocalDate datumOd,
                             LocalDate datumDo) {

        for (LocalDate d = datumOd; !d.isAfter(datumDo); d = d.plusDays(1)) {
            spremiJedanDan(radnikId, vrstaUnosa, smjena, d);
        }
    }

    @Override
    public List<Raspored> dohvatiRasporedZaRadnikaUTjednu(Long radnikId,
                                                          LocalDate ponedjeljak) {

        LocalDate nedjelja = ponedjeljak.plusDays(6);

        return rasporedRepository
                .findByRadnikIdAndDatumBetween(radnikId, ponedjeljak, nedjelja);
    }

    @Override
    public void obrisi(Long rasporedId) {
        rasporedRepository.deleteById(rasporedId);
    }

    @Override
    public List<Raspored> findAll() {
        return rasporedRepository.findAll();
    }
}
