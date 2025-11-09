package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zivotinja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ime;
    private String latinskiNaziv;
    private String tip;
    private String nacinNabave;
    private LocalDate datumUnosa;
    private boolean aktivna = true;

}
