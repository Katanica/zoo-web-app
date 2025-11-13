package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OBAVEZA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Obaveza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obaveze")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipa", nullable = false)
    private TipObaveze tip;

    @ManyToOne
    @JoinColumn(name = "id_statusa", nullable = false)
    private StatusObaveze status;

    @ManyToOne
    @JoinColumn(name = "id_radnika")
    private Radnik radnik;  // moze biti null

    @ManyToOne
    @JoinColumn(name = "id_jedinke")
    private Jedinka jedinka;

    @ManyToOne
    @JoinColumn(name = "id_skupine")
    private Skupina skupina;

    @Column(name = "datum_od")
    private LocalDateTime datumOd;

    @Column(name = "datum_do")
    private LocalDateTime datumDo;

    @Column(name = "komentar")
    private String komentar;
}
