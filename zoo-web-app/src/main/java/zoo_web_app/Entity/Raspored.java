package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zoo_web_app.Entity.Radnik;
import zoo_web_app.Entity.Smjena;
import zoo_web_app.Entity.VrstaUnosaRasporeda;

import java.time.LocalDate;

@Entity
@Table(
        name = "raspored",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_raspored_radnik_datum",
                        columnNames = {"radnik_id", "datum"}
                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Raspored {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "radnik_id", nullable = false)
    private Radnik radnik;

    @Column(nullable = false)
    private LocalDate datum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VrstaUnosaRasporeda vrstaUnosa;

    @Enumerated(EnumType.STRING)
    private Smjena smjena; // NULL ako nije RAD

}
