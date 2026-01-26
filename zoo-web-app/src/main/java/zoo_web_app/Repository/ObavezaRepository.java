package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zoo_web_app.Entity.Obaveza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zoo_web_app.Entity.Radnik;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ObavezaRepository extends JpaRepository<Obaveza, Long> {
    List<Obaveza> findByJedinkaId(Long idJedinke);

    List<Obaveza> findByRadnikId(Long idRadnika);

    List<Obaveza> findBySkupinaId(Long idSkupine);
}
