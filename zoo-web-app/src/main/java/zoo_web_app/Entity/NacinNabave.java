package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "NACIN_NABAVE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NacinNabave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nabave")
    private Long id;

    @Column(name = "opis_nabave", nullable = false, unique = true)
    private String opisNabave;
    /*
    @OneToMany(mappedBy = "nacinNabave")
    private List<Jedinka> jedinke;

    @OneToMany(mappedBy = "nacinNabave")
    private List<Skupina> skupine;*/
}