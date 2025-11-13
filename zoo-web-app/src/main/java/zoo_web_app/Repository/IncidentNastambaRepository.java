package zoo_web_app.Repository;

import zoo_web_app.Entity.IncidentNastamba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentNastambaRepository extends JpaRepository<IncidentNastamba, Long> {
}