package zoo_web_app.Repository;

import zoo_web_app.Entity.Skupina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkupinaRepository extends JpaRepository<Skupina, Long> {
}