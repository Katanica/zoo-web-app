package zoo_web_app.Repository;

import zoo_web_app.Entity.Obrazovanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObrazovanjeRepository extends JpaRepository<Obrazovanje, Long> {
    Optional<Obrazovanje> findByNaziv(String naziv);
}
