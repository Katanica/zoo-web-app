package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "RAD_NA_INCIDENTU")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RadNaIncidentu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rad_na_incidentu")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_incidenta", nullable = false)
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "id_radnika", nullable = false)
    private Radnik radnik;

    @Column(name = "opis_rada")
    private String opisRada;

    @Column(name = "datum_rada")
    private LocalDate datumRada;

    @Column(name = "trajanje_sati")
    private double trajanjeSati;

    @Column(name = "cijena_sata")
    private double cijenaSata;

    @Column(name = "ukupni_trosak")
    private double ukupniTrosak;
}
