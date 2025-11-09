package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nastamba {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id_nastambe;

    private String oznaka;
    private String geometrija;

    @ManyToOne
    @JoinColumn(name = "id_zoo")
    private Zoo zoo;

    @ManyToMany
    @JoinTable(
            name = "nastamba_karakteristika",
            joinColumns = @JoinColumn(name = "id_nastambe"),
            inverseJoinColumns = @JoinColumn(name = "id_karakteristike")
    )
    private List<Karakteristika> karakteristike;

    @ManyToMany
    @JoinTable(
            name="nastamba_zivotinja",
            joinColumns = @JoinColumn(name="id_nastambe"),
            inverseJoinColumns = @JoinColumn(name = "id_zivotinje")
    )
    private List<Zivotinja> zivotinje;
}
