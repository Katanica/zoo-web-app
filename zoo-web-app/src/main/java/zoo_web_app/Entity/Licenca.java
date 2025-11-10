package zoo_web_app.Entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Licenca {
    private String naziv;
    private LocalDate datumIzdavanja;
    private LocalDate datumIsteka;

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public LocalDate getDatumIzdavanja() { return datumIzdavanja; }
    public void setDatumIzdavanja(LocalDate datumIzdavanja) { this.datumIzdavanja = datumIzdavanja; }
    public LocalDate getDatumIsteka() { return datumIsteka; }
    public void setDatumIsteka(LocalDate datumIsteka) { this.datumIsteka = datumIsteka; }
}