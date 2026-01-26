package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "JEDINKA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jedinka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jedinke")
    private Long id;



    @Column(name = "nadimak")
    private String nadimak;

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

    @Column(name = "aktivna"/*, nullable = false*/)
    private boolean aktivna = true;

    @Column(name = "datum_nabave")
    private LocalDate datumNabave;

    @Column(name = "datum_neaktivnosti")
    private LocalDate datumNeaktivnosti;

    @Column(name = "razlog_neaktivnosti")
    private String razlogNeaktivnosti;

    @OneToMany(mappedBy = "jedinka")
    @JsonIgnore
    private List<Obaveza> obaveze;

    @OneToMany(mappedBy = "jedinka")
    @JsonIgnore
    private List<Trosak> troskovi;

    @OneToMany(mappedBy = "jedinka")
    @JsonIgnore
    private List<IncidentJedinka> incidenti;

    public String sastaviIme(){
        return hrvatskiNaziv + " " + nadimak;
    }
}
