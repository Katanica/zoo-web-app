package zoo_web_app.Repository;

import zoo_web_app.Entity.NastambaInfrastruktura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastambaInfrastrukturaRepository extends JpaRepository<NastambaInfrastruktura, Long> {
}
