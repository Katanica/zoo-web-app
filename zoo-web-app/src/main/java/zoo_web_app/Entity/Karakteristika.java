package zoo_web_app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
