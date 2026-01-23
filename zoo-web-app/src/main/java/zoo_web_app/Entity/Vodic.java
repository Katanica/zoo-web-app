package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "VODIC")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vodic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vodica")
    private Long id;

    @Column(name = "ime", nullable = false)
    private String ime;

    @Column(name = "prezime", nullable = false)
    private String prezime;

    // opcionalno (ako želite)
    @Column(name = "status")
    private String status;

    @Column(name = "satnica")
    private Double satnica;
}
