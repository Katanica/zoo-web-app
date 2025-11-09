package zoo_web_app.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public String getObrazovanje() { return obrazovanje; }
    public void setObrazovanje(String obrazovanje) { this.obrazovanje = obrazovanje; }
    public String getKvalifikacije() { return kvalifikacije; }
    public void setKvalifikacije(String kvalifikacije) { this.kvalifikacije = kvalifikacije; }
    public StatusZaposlenja getStatus() { return status; }
    public void setStatus(StatusZaposlenja status) { this.status = status; }
    public List<Licenca> getLicence() { return licence; }
    public void setLicence(List<Licenca> licence) { this.licence = licence; }
    public List<Smjena> getSmjene() { return smjene; }
    public void setSmjene(List<Smjena> smjene) { this.smjene = smjene; }
    public List<Bolovanje> getBolovanja() { return bolovanja; }
    public void setBolovanja(List<Bolovanje> bolovanja) { this.bolovanja = bolovanja; }
    public List<GodisnjiOdmor> getGodisnjiOdmori() { return godisnjiOdmori; }
    public void setGodisnjiOdmori(List<GodisnjiOdmor> godisnjiOdmori) { this.godisnjiOdmori = godisnjiOdmori; }
}