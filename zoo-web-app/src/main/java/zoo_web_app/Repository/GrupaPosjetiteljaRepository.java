package zoo_web_app.Repository;

import zoo_web_app.Entity.GrupaPosjetitelja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupaPosjetiteljaRepository extends JpaRepository<GrupaPosjetitelja, Long> {
}