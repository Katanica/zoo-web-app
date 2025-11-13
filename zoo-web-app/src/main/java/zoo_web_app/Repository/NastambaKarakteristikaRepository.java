package zoo_web_app.Repository;

import zoo_web_app.Entity.NastambaKarakteristika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastambaKarakteristikaRepository extends JpaRepository<NastambaKarakteristika, Long> {
}