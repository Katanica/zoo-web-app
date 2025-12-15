package zoo_web_app.Service;

import zoo_web_app.Entity.Raspored;
import zoo_web_app.Entity.Smjena;
import zoo_web_app.Entity.VrstaUnosaRasporeda;
import zoo_web_app.Repository.RasporedRepository;

import java.time.LocalDate;
import java.util.List;


public interface RasporedService {

    void spremiJedanDan(Long radnikId,
                        VrstaUnosaRasporeda vrstaUnosa,
                        Smjena smjena,
                        LocalDate datum);

    void spremiRaspon(Long radnikId,
                      VrstaUnosaRasporeda vrstaUnosa,
                      Smjena smjena,
                      LocalDate datumOd,
                      LocalDate datumDo);

    List<Raspored> dohvatiRasporedZaRadnikaUTjednu(Long radnikId,
                                                   LocalDate ponedjeljak);

    void obrisi(Long rasporedId);

    List<Raspored> findAll();

    List<Raspored> dohvatiPoPonedjeljku(LocalDate ponedjeljak);
}

