package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Obaveza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipObaveze tip;

    @Enumerated(EnumType.STRING)
    private StatusObaveze status = StatusObaveze.PLANIRANA;

    private String opis; // npr Hranjenje lavova, Veterinarski pregled itdddd


    // vrijeme kad treba biti obavljena ( moze biti i null )

    private LocalDateTime pocetak;
    private LocalDateTime kraj;

    //radnik koji je zaduzen za obavezu ( moze biti null dok ga ne odredis)

    @ManyToOne
    @JoinColumn(name = " zaposlenik_id")
    private Zaposlenik zaposlenik;

    //povezivanje sa zivotinjom ili nastambom

    @ManyToOne
    @JoinColumn(name = "zivotinja_id")
    private Zivotinja zivotinja;

    @ManyToOne
    @JoinColumn(name = "nastamba_id")
    private Nastamba nastamba;



}
