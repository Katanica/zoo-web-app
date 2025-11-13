package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "TIP_OBAVEZE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipObaveze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipa")
    private Long id;

    @Column(name = "opis_obveze", nullable = false)
    private String opisObveze;

    @OneToMany(mappedBy = "tip")
    private List<Obaveza> obaveze;
}
