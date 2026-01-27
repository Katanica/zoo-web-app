package zoo_web_app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

@Entity
@Table(name = "RADNIK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Radnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_radnika")
    private Long id;

    @Column(name = "ime", nullable = false)
    private String ime;

    @Column(name = "prezime", nullable = false)
    private String prezime;

    @Column(name = "telefon", nullable = false)
    private String telefon;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;


    @JsonIgnore
    @OneToMany(mappedBy = "radnik")
    private List<Obaveza> obaveze;

    @JsonIgnore
    @OneToMany(mappedBy = "vodic")
    private List<GrupaPosjetitelja> grupe;

    @JsonIgnore
    @OneToMany(mappedBy="radnik", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RadnikObrazovanje> obrazovanja = new ArrayList<>();


} 