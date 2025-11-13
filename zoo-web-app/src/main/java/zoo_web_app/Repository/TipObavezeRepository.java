package zoo_web_app.Repository;

import zoo_web_app.Entity.TipObaveze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipObavezeRepository extends JpaRepository<TipObaveze, Long> {
}
