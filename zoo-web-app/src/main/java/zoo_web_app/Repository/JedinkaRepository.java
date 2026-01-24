package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.Query;
import zoo_web_app.Entity.Jedinka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JedinkaRepository extends JpaRepository<Jedinka, Long> {

    @Query("SELECT j from Jedinka j where j.aktivna = true")
    List<Jedinka> findAllAktivne();
    long countByAktivnaTrue();
}