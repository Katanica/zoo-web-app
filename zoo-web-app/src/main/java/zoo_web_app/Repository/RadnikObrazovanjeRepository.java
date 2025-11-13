package zoo_web_app.Repository;

import zoo_web_app.Entity.RadnikObrazovanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadnikObrazovanjeRepository extends JpaRepository<RadnikObrazovanje, Long> {
}
