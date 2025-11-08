package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zoo_web_app.Entity.Zoo;

public interface ZooRepository extends JpaRepository<Zoo, Long> {
}
