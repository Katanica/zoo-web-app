package zoo_web_app.Repository;

import zoo_web_app.Entity.Radnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadnikRepository extends JpaRepository<Radnik, Long> {
}