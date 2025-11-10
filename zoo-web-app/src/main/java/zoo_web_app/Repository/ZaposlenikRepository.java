package zoo_web_app.Repository;

import zoo_web_app.Entity.Zaposlenik;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ZaposlenikRepository extends JpaRepository<Zaposlenik, Long> {
    List<Zaposlenik> findByKvalifikacijeContainingIgnoreCase(String kvalifikacija);
}