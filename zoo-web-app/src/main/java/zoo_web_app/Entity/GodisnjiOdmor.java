package zoo_web_app.Entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class GodisnjiOdmor {
    private LocalDate od;
    private LocalDate doDatum;
    private int dani;

    public LocalDate getOd() { return od; }
    public void setOd(LocalDate od) { this.od = od; }
    public LocalDate getDoDatum() { return doDatum; }
    public void setDoDatum(LocalDate doDatum) { this.doDatum = doDatum; }
    public int getDani() { return dani; }
    public void setDani(int dani) { this.dani = dani; }
}