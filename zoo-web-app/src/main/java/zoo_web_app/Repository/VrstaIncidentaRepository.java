package zoo_web_app.Repository;

import zoo_web_app.Entity.VrstaIncidenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VrstaIncidentaRepository extends JpaRepository<VrstaIncidenta, Long> {
    List<VrstaIncidenta> findAllByNazivIgnoreCase(String naziv);
}