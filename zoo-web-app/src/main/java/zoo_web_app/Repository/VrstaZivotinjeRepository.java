package zoo_web_app.Repository;

import zoo_web_app.Entity.VrstaZivotinje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VrstaZivotinjeRepository extends JpaRepository<VrstaZivotinje, Long> {
}