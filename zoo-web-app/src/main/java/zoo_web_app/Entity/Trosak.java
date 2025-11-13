package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TROSAK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trosak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_troska")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_jedinke")
    private Jedinka jedinka;

    @ManyToOne
    @JoinColumn(name = "id_skupine")
    private Skupina skupina;

    @Column(name = "tip_troska", nullable = false)
    private String tipTroska; // npr. "EUR" ili "SATI"

    @Column(name = "iznos", nullable = false)
    private BigDecimal iznos;

    @Column(name = "opis")
    private String opis;

    @Column(name = "datum")
    private LocalDate datum;
}
