package zoo_web_app.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zoo_web_app.Entity.VrstaIncidenta;
import zoo_web_app.Repository.VrstaIncidentaRepository;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final VrstaIncidentaRepository repo;

    public DataSeeder(VrstaIncidentaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void run(String... args) {
        seedAndCleanup("Vandalizam");
        seedAndCleanup("Ozljeda");
        seedAndCleanup("Kvar");
        seedAndCleanup("Vremenska nepogoda");
    }

    private void seedAndCleanup(String naziv) {
        List<VrstaIncidenta> all = repo.findAllByNazivIgnoreCase(naziv);

        if (all.isEmpty()) {
            repo.save(new VrstaIncidenta(null, naziv, null));
            return;
        }

        // Ako ih ima više, ostavi jednu, ostale obriši
        VrstaIncidenta keep = all.get(0);

        for (int i = 1; i < all.size(); i++) {
            VrstaIncidenta candidate = all.get(i);

            // Brisati samo ako nema incidenata (da ne pukne FK)
            if (candidate.getIncidenti() == null || candidate.getIncidenti().isEmpty()) {
                repo.delete(candidate);
            } else {
                // Ako ovaj ima incidente, bolje njega zadržati,
                // pa pokušati obrisati "keep" ako je prazan
                if (keep.getIncidenti() == null || keep.getIncidenti().isEmpty()) {
                    repo.delete(keep);
                    keep = candidate;
                }
            }
        }
    }

}