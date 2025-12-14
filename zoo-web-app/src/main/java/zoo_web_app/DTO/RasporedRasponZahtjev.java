package zoo_web_app.DTO;

import zoo_web_app.Entity.Smjena;
import zoo_web_app.Entity.VrstaUnosaRasporeda;

import java.time.LocalDate;

public class RasporedRasponZahtjev {

    public Long radnikId;
    public VrstaUnosaRasporeda vrstaUnosa;
    public Smjena smjena;
    public LocalDate datumOd;
    public LocalDate datumDo;
}
