package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "INFRASTRUKTURA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infrastruktura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_infrastrukture")
    private Long id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @Column(name = "kategorija")
    private String kategorija; // npr. PREDMET, BILJKA...

    @OneToMany(mappedBy = "infrastruktura")
    private List<NastambaInfrastruktura> nastambe;
}
