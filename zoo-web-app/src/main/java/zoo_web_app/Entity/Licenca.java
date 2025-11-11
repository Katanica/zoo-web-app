package zoo_web_app.Entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Licenca {
    private String naziv;
    private LocalDate datumIzdavanja;
    private LocalDate datumIsteka;
}