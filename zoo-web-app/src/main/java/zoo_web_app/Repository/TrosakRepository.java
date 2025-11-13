package zoo_web_app.Repository;

import zoo_web_app.Entity.Trosak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrosakRepository extends JpaRepository<Trosak, Long> {
}