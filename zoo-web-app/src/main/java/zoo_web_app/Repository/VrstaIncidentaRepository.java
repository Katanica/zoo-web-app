package zoo_web_app.Repository;

import zoo_web_app.Entity.VrstaIncidenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VrstaIncidentaRepository extends JpaRepository<VrstaIncidenta, Long> {
}