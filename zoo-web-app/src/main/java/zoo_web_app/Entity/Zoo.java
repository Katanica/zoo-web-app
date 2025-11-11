package zoo_web_app.Entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Zoo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String adresa;
    private String telefon;
    private String mail;

    @OneToMany(mappedBy ="zoo")
    private List<Nastamba> nastambe;
}
