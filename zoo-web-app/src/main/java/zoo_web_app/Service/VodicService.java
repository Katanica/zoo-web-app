package zoo_web_app.Service;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Vodic;
import zoo_web_app.Repository.VodicRepository;

import java.util.List;

@Service
public class VodicService {

    private final VodicRepository repo;

    public VodicService(VodicRepository repo) {
        this.repo = repo;
    }

    public List<Vodic> findAll() {
        return repo.findAll();
    }

    public Vodic create(Vodic v) {
        return repo.save(v);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
