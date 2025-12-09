package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "karakteristika")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Karakteristika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_karakteristike")
    private Long id;

    @Column(name = "naziv", nullable = false, unique = true)
    private String naziv;

    @ManyToMany(mappedBy = "karakteristike")
    @JsonIgnore
    private List<Nastamba> nastambe;
}
