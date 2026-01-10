package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "INFRASTRUKTURA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infrastruktura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_infrastrukture")
    private Long id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @ManyToMany(mappedBy = "infrastruktura")
    @JsonIgnore
    private List<Nastamba> nastambe;
}
