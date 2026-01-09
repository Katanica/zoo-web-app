package zoo_web_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkupinaDTO {

    private Long id;
    private String identifikator;
    private String latinskiNaziv;
    private String hrvatskiNaziv;

    // dolazi iz Nastamba.oznaka
    private String nastambaOznaka;

    private Integer procijenjeniBroj;
    private boolean aktivna;
}
