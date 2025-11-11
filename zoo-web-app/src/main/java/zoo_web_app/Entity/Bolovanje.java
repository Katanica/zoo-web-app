package zoo_web_app.Entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Bolovanje {
    private LocalDate od;
    private LocalDate doDatum;
    private String razlog;
}