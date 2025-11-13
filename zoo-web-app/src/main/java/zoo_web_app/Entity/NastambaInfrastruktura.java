package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "NASTAMBA_INFRASTRUKTURA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NastambaInfrastruktura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nastamba_infrastruktura")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_nastambe", nullable = false)
    private Nastamba nastamba;

    @ManyToOne
    @JoinColumn(name = "id_infrastrukture", nullable = false)
    private Infrastruktura infrastruktura;

    @Column(name = "kolicina")
    private Integer kolicina;
}
