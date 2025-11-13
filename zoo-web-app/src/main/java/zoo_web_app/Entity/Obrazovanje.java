package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "OBRAZOVANJE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Obrazovanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obrazovanja")
    private Long id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @OneToMany(mappedBy = "obrazovanje")
    private List<RadnikObrazovanje> radnici;
}
