package zoo_web_app.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zoo_web_app.Repository.NastambaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObavezaService {

    private final ObavezaRepository repo;
    private final NastambaRepository nastambaRepo;
    private final ZivotinjaRepository zivotinjaRepo;


    // 🔹 Dohvati sve obaveze
    public List<Obaveza> getAll() {
        return repo.findAll();
    }

    // 🔹 Kreiraj novu obavezu (planiranu)
    public Obaveza create(Obaveza obaveza) {
        obaveza.setStatus(StatusObaveze.PLANIRANA);
        if (obaveza.getNastamba() != null && obaveza.getNastamba().getId_nastambe() != 0) {
            var n = nastambaRepo.findById(obaveza.getNastamba().getId_nastambe())
                    .orElseThrow(() -> new IllegalStateException("Nastamba nije pronađena!"));
            obaveza.setNastamba(n);
        }

        if (obaveza.getZivotinja() != null && obaveza.getZivotinja().getId() != null) {
            var z = zivotinjaRepo.findById(obaveza.getZivotinja().getId())
                    .orElseThrow(() -> new IllegalStateException("Životinja nije pronađena!"));
            obaveza.setZivotinja(z);
        }
        return repo.save(obaveza);
    }

    // 🔹 Otkazivanje obaveze
    public void otkaziObavezu(Long id) {
        Obaveza o = repo.findById(id).orElseThrow();
        o.setStatus(StatusObaveze.OTKAZANA);
        repo.save(o);
    }

    // 🔹 Označi obavezu kao odrađenu
    public void odradiObavezu(Long id) {
        Obaveza o = repo.findById(id).orElseThrow();
        o.setStatus(StatusObaveze.ODRADENA);
        repo.save(o);
    }

    // 🔹 Dodjela radnika i termina
    public Obaveza dodijeliZaposlenikaIVrijeme(
            Long obavezaId,
            Long zaposlenikId,
            LocalDateTime pocetak,
            LocalDateTime kraj) {

        Obaveza o = repo.findById(obavezaId).orElseThrow();

        if (zaposlenikId != null && pocetak != null && kraj != null) {
            var konflikti = repo.findKonflikti(zaposlenikId, pocetak, kraj, obavezaId);
            if (!konflikti.isEmpty()) {
                throw new IllegalStateException("Zaposlenik već ima obavezu u tom terminu!");
            }
        }

        o.setPocetak(pocetak);
        o.setKraj(kraj);
        repo.save(o);
        return o;
    }

    // 🔹 Brisanje obaveze (ako želiš soft delete, možeš promijeniti logiku)
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
