package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INCIDENT_NASTAMBA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentNastamba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incident_nastambe")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_incidenta", nullable = false)
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "id_nastambe", nullable = false)
    private Nastamba nastamba;

    @Column(name = "razina_stete")
    private String razinaStete;
}
