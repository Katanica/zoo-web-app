package zoo_web_app.Entity;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Karakteristika {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_karakteristike;

    private String naziv_karakteristike;


}
