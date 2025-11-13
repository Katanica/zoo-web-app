package zoo_web_app.Repository;

import zoo_web_app.Entity.Nastamba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastambaRepository extends JpaRepository<Nastamba, Long> {
}


