package zoo_web_app.Entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Bolovanje {
    private LocalDate od;
    private LocalDate doDatum;
    private String razlog;

    public LocalDate getOd() { return od; }
    public void setOd(LocalDate od) { this.od = od; }
    public LocalDate getDoDatum() { return doDatum; }
    public void setDoDatum(LocalDate doDatum) { this.doDatum = doDatum; }
    public String getRazlog() { return razlog; }
    public void setRazlog(String razlog) { this.razlog = razlog; }
}