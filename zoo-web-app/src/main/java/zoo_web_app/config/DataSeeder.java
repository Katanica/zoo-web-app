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
        
        VrstaIncidenta keep = all.get(0);

        for (int i = 1; i < all.size(); i++) {
            VrstaIncidenta candidate = all.get(i);

            if (candidate.getIncidenti() == null || candidate.getIncidenti().isEmpty()) {
                repo.delete(candidate);
            } else {
                if (keep.getIncidenti() == null || keep.getIncidenti().isEmpty()) {
                    repo.delete(keep);
                    keep = candidate;
                }
            }
        }
    }

}