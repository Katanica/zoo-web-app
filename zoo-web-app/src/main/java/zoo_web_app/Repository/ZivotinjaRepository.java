package zoo_web_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zoo_web_app.Entity.Zivotinja;

import java.util.List;


public interface ZivotinjaRepository extends JpaRepository<Zivotinja, Long>{


    List<Zivotinja> findByAktivnaTrue();
}
