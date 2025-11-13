package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "RADNIK_OBRAZOVANJE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RadnikObrazovanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_radnik_obrazovanje")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_radnika", nullable = false)
    private Radnik radnik;

    @ManyToOne
    @JoinColumn(name = "id_obrazovanja", nullable = false)
    private Obrazovanje obrazovanje;

    @Column(name = "datum_stjecanja")
    private LocalDate datumStjecanja;
}
