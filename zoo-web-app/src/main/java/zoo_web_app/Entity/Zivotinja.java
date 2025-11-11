package zoo_web_app.Entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

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
