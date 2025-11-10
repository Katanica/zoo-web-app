package zoo_web_app.Entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Smjena {
    private LocalDate datum;
    private String tip;

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }
    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }
}