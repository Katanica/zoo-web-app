package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zoo_web_app.Entity.RadNaIncidentu;

import java.util.List;

@Repository
public interface RadNaIncidentuRepository extends JpaRepository<RadNaIncidentu, Long> {

    List<RadNaIncidentu> findByIncidentId(Long incidentId);
}
