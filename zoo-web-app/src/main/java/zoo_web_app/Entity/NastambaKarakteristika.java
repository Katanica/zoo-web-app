package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "NASTAMBA_KARAKTERISTIKA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NastambaKarakteristika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nastamba_karakteristika")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_nastambe", nullable = false)
    private Nastamba nastamba;

    @ManyToOne
    @JoinColumn(name = "id_karakteristike", nullable = false)
    private Karakteristika karakteristika;
}
