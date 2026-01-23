package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INCIDENT_SKUPINA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentSkupina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incident_skupina")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_incidenta", nullable = false)
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "id_skupine", nullable = false)
    private Skupina skupina;

    @Column(name = "razina_stete")
    private String razinaStete;
}
