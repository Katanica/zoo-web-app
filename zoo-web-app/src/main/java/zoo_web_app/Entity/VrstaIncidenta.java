package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "naziv", nullable = false, unique = true)
    private String naziv;

    @JsonIgnore
    @OneToMany(mappedBy = "vrsta")
    private List<Incident> incidenti;
}
