package zoo_web_app.Repository;

import zoo_web_app.Entity.Karakteristika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KarakteristikaRepository extends JpaRepository<Karakteristika, Long> {
}
