package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.Query;
import zoo_web_app.Entity.Skupina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkupinaRepository extends JpaRepository<Skupina, Long> {

    @Query("SELECT s FROM Skupina s WHERE s.aktivna = true")
    List<Skupina> findAllAktivne();

    long countByAktivnaTrue();

    List<Skupina> findByAktivnaTrue();
}