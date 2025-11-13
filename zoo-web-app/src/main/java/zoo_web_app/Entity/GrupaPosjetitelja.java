package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "GRUPA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupaPosjetitelja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupe")
    private Long id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @Column(name = "kontakt_mail")
    private String kontaktMail;

    @Column(name = "broj_osoba")
    private Integer brojOsoba;

    @Column(name = "datum_dolaska")
    private LocalDateTime datumDolaska;

    @Column(name = "vrijeme_pocetka")
    private LocalDateTime vrijemePocetka;

    @Column(name = "vrijeme_kraja")
    private LocalDateTime vrijemeKraja;

    @ManyToOne
    @JoinColumn(name = "id_vodica")
    private Radnik vodic;

    @Column(name = "status")
    private String status; // NAJAVLJENO, VODIC_DODIJELJEN...
}
