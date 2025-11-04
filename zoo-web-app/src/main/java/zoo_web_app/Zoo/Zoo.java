package zoo_web_app.Zoo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
