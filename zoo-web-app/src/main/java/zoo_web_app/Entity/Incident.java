package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "INCIDENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidenta")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vrste", nullable = false)
    private VrstaIncidenta vrsta;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "opis")
    private String opis;

    @OneToMany(mappedBy = "incident")
    private List<IncidentNastamba> nastambe;

    @OneToMany(mappedBy = "incident")
    private List<IncidentJedinka> jedinke;

    @OneToMany(mappedBy = "incident")
    private List<IncidentSkupina> skupine;
}
