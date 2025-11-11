package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zoo_web_app.Entity.Obaveza;

import java.time.LocalDateTime;
import java.util.List;

public interface ObavezaRepository extends JpaRepository<Obaveza, Long> {

    @Query("""
        SELECT o FROM Obaveza o
        WHERE o.zaposlenik.id = :zaposlenikId
          AND o.status <> zoo_web_app.Entity.StatusObaveze.OTKAZANA
          AND o.pocetak < :kraj
          AND o.kraj > :pocetak
          AND (:obavezaId IS NULL OR o.id <> :obavezaId)
    """)
    List<Obaveza> findKonflikti(Long zaposlenikId,
                                LocalDateTime pocetak,
                                LocalDateTime kraj,
                                Long obavezaId);
}
