package zoo_web_app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
