package zoo_web_app.Service;

import org.springframework.stereotype.Service;
import zoo_web_app.Controller.IzvjestajController;

@Service
public interface IzvjestajService {
    byte[] generirajTroskoviPdf(IzvjestajController.TroskoviPdfRequest req);
    byte[] generirajRadniciPdf(IzvjestajController.RadniciPdfRequest req);
}
