package zoo_web_app.Enclosure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Enclosure {
    @Id
    private String label;

    private String geometry;
    private String characteristics;
    private String contents;

    // ash - animal's secondary habitat
    private Boolean ash;
}
