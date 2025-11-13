package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "STATUS_OBAVEZE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusObaveze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statusa")
    private Long id;

    @Column(name = "naziv_statusa", nullable = false)
    private String nazivStatusa; // PLANIRANO, OBAVLJENO, OTKAZANO...

    @OneToMany(mappedBy = "status")
    private List<Obaveza> obaveze;
}
