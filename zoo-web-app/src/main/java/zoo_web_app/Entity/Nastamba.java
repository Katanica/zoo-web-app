package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "NASTAMBA")
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

    @OneToMany(mappedBy = "nastamba")
    private List<NastambaKarakteristika> karakteristike;

    @OneToMany(mappedBy = "nastamba")
    private List<NastambaInfrastruktura> infrastruktura;

    @OneToMany(mappedBy = "nastamba")
    @JsonIgnore
    private List<Jedinka> jedinke;

    @OneToMany(mappedBy = "nastamba")
    @JsonIgnore
    private List<Skupina> skupine;
}
