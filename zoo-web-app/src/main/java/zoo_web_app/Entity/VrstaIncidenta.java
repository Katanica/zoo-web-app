package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "VRSTA_INCIDENTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VrstaIncidenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vrste")
    private Long id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @OneToMany(mappedBy = "vrsta")
    private List<Incident> incidenti;
}
