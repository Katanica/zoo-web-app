package zoo_web_app.Repository;

import zoo_web_app.Entity.Obrazovanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObrazovanjeRepository extends JpaRepository<Obrazovanje, Long> {
}
