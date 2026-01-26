package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


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

    private TipObaveze tip;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusObaveze status;

    @ManyToOne
    @JoinColumn(name = "id_radnika")
    private Radnik radnik;  // moze biti null

    @ManyToOne
    @JoinColumn(name = "id_jedinke", nullable = true)
    private Jedinka jedinka;

    @ManyToOne
    @JoinColumn(name = "id_skupine", nullable = true)
    private Skupina skupina;

    private boolean active = true;
    private RepeatType repeatType; // NONE, DAILY, MONTHLY!!!
    private Integer repeatEvery; // npr. 1 dan, 3 mjeseca... (null ako NONE)

    @Column(name = "vrijeme_od")
    private LocalDateTime vrijemeOd;

    @Column(name = "vrijeme_do")
    private LocalDateTime vrijemeDo;

    @Column(name = "komentar")
    private String komentar;

    public Long getTrajanje() {
        return ChronoUnit.MINUTES.between(vrijemeOd, vrijemeDo);
    }
}
