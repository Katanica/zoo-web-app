package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "SKUPINA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skupina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skupine")
    private Long id;

    @Column(name = "identifikator"/*, nullable = false*/)
    private String identifikator;

    @Column(name = "latinski_naziv"/*, nullable = false*/)
    private String latinskiNaziv;

    @Column(name = "hrvatski_naziv"/*, nullable = false*/)
    private String hrvatskiNaziv;

    @ManyToOne
    @JoinColumn(name = "id_nastambe"/*, nullable = false*/)
    private Nastamba nastamba;

    @ManyToOne
    @JoinColumn(name = "id_nabave"/*, nullable = false*/)
    private NacinNabave nacinNabave;

    @Column(name="link")
    private String link;

    @Column(name = "procijenjeni_broj")
    private Integer procijenjeniBroj;

    @Column(name = "datum_nabave")
    private LocalDate datumNabave;

    @Column(name = "aktivna", nullable = false)
    private boolean aktivna = true;

    @Column(name = "razlog_neaktivnosti")
    private String razlogNeaktivnosti;

    @OneToMany(mappedBy = "skupina")
    private List<Obaveza> obaveze;

    @OneToMany(mappedBy = "skupina")
    private List<Trosak> troskovi;

    @OneToMany(mappedBy = "skupina")
    private List<IncidentSkupina> incidenti;
}
