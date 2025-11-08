package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
// automatski definira get-ere, set-ere...
@Data
@AllArgsConstructor // konstruktor sa svim atributima
@NoArgsConstructor  // prazan konstruktor (bez atributa)
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
