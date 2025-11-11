package zoo_web_app.Entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Smjena {
    private LocalDate datum;
    private String tip;

}