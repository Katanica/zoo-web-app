package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zoo_web_app.Entity.Obaveza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ObavezaRepository extends JpaRepository<Obaveza, Long> {

@Query("""
SELECT o FROM Obaveza o 
WHERE o.radnik.id = :idRadnika
AND o.statusObaveze <> 'OTKAZANA'
AND (
(o.datumOd <= :datumDo AND o.datumDo >= :datumOd)
)
""")
List<Obaveza> provjeriPreklapanje(
            @Param("idRadnika") Long idRadnika,
            @Param("datumOd") LocalDate datumOd,
            @Param("datumDo") LocalDate datumDo

);


}
