package zoo_web_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RadnikList {
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private String status;
    private String obrazovanje;
}