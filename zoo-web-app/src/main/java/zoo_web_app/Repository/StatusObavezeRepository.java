package zoo_web_app.Repository;


import zoo_web_app.Entity.StatusObaveze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusObavezeRepository extends JpaRepository<StatusObaveze, Long> {
}
