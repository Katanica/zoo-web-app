package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zoo_web_app.Entity.Raspored;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RasporedRepository extends JpaRepository<Raspored, Long> {

    Optional<Raspored> findByRadnikIdAndDatum(Long radnikId, LocalDate datum);

    List<Raspored> findByRadnikIdAndDatumBetween(Long radnikId, LocalDate datumOd, LocalDate datumDo);

    List<Raspored> findByDatumBetween(LocalDate ponedjeljak, LocalDate nedjelja);
}
