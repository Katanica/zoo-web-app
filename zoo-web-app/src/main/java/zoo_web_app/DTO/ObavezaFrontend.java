package zoo_web_app.DTO;

import lombok.Data;
import zoo_web_app.Entity.RepeatType;
import zoo_web_app.Entity.StatusObaveze;
import zoo_web_app.Entity.TipObaveze;

import java.time.LocalDateTime;

@Data
public class ObavezaFrontend {
    private TipObaveze tip;
    private StatusObaveze status;
    private Long radnikId;
    private Long idJedinke;
    private Long idSkupine;
    private RepeatType repeatType;
    private Integer repeatEvery;
    private Integer trajanje;
    private LocalDateTime vrijemeOd;
    private LocalDateTime vrijemeDo;
    private String komentar;
}
