package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "nastamba")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nastamba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nastambe")
    private Long id;

    @Column(name = "oznaka", nullable = false, unique = true)
    private String oznaka;

    @Column(name = "opis")
    private String opis;

    @Column(name = "geometrija")
    private String geometrija;

    @Column(name = "aktivna")
    private boolean aktivna = true;

    @ManyToMany
    @JoinTable(
            name="nastamba_karakteristika",
            joinColumns = @JoinColumn(name = "id_nastambe"),
            inverseJoinColumns = @JoinColumn(name = "id_karakteristike")
    )
    private List<Karakteristika> karakteristike;

    @ManyToMany
    @JoinTable(
            name="nastamba_infrastruktura",
            joinColumns= @JoinColumn(name="id_nastambe"),
            inverseJoinColumns = @JoinColumn(name="id_infrastrukture")
    )
    private List<Infrastruktura> infrastruktura;
}
