package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INCIDENT_JEDINKA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentJedinka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incident_jedinka")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_incidenta", nullable = false)
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "id_jedinke", nullable = false)
    private Jedinka jedinka;

    @Column(name = "razina_stete")
    private String razinaStete;
}
