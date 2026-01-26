package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zoo_web_app.Entity.Radnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RadnikRepository extends JpaRepository<Radnik, Long> {
    @Query("""
select distinct r from Radnik r
left join fetch r.obrazovanja ro
left join fetch ro.obrazovanje o
""")
    List<Radnik> findAllWithObrazovanja();

    @Query("SELECT r.ime FROM Radnik r WHERE r.id = :id")
    String findImeById(@Param("id") Long id);

    @Query("SELECT r.prezime FROM Radnik r WHERE r.id = :id")
    String findPrezimeById(@Param("id") Long id);
}