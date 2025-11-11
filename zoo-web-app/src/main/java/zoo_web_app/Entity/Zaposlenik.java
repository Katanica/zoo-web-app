package zoo_web_app.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "zaposlenik")
public class Zaposlenik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefon;

    private String obrazovanje;
    private String kvalifikacije;

    @Enumerated(EnumType.STRING)
    private StatusZaposlenja status;

    @ElementCollection
    @CollectionTable(name = "zaposlenik_licence", joinColumns = @JoinColumn(name = "zaposlenik_id"))
    private List<Licenca> licence = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "zaposlenik_smjene", joinColumns = @JoinColumn(name = "zaposlenik_id"))
    private List<Smjena> smjene = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "zaposlenik_bolovanja", joinColumns = @JoinColumn(name = "zaposlenik_id"))
    private List<Bolovanje> bolovanja = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "zaposlenik_godisnji", joinColumns = @JoinColumn(name = "zaposlenik_id"))
    private List<GodisnjiOdmor> godisnjiOdmori = new ArrayList<>();


}