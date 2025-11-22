package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "VRSTA_ZIVOTINJE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VrstaZivotinje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vrste")
    private Long id;

    @Column(name = "latinski_naziv", nullable = false)
    private String latinskiNaziv;

    @Column(name = "hrvatski_naziv", nullable = false)
    private String hrvatskiNaziv;

    @Column(name = "link", length = 500)
    private String link;

    /*@OneToMany(mappedBy = "vrsta")
    private List<Skupina> skupine;*/
}
