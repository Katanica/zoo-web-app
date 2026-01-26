package zoo_web_app.Controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import zoo_web_app.Service.IzvjestajService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/izvjestaj")
public class IzvjestajController {

    private final IzvjestajService izvjestajService;

    public IzvjestajController(IzvjestajService izvjestajService) {
        this.izvjestajService = izvjestajService;
    }

    // 1) IZVJEŠTAJ TROŠKOVA: životinje/skupine -> PDF
    @PostMapping(value = "/troskovi/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> troskoviPdf(@RequestBody @NotNull TroskoviPdfRequest req) {

        byte[] pdf = izvjestajService.generirajTroskoviPdf(req);

        String filename = "izvjestaj_troskovi_" + req.scope + "_" + req.mode  + "_" + req.dateFrom + "-" + req.dateTo + ".pdf";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(pdf);
    }

    // 2) IZVJEŠTAJ RADNIKA: radni sati radnika -> PDF
    @PostMapping(value = "/radnici/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> radniciPdf(@RequestBody @NotNull RadniciPdfRequest req) {

        byte[] pdf = izvjestajService.generirajRadniciPdf(req);

        String filename = "izvjestaj_radnici_" + req.scope + "_" + req.dateFrom + "-" + req.dateTo + ".pdf";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(pdf);
    }

    // ---- DTOs (možeš ih izvući u posebne file-ove) ----

    public enum Scope { ONE, ALL }
    public enum AnimalType { JEDINKA, SKUPINA }
    public enum CostMode { HOURS, MONEY, BOTH }

    public enum SortTroskovi {
        MONEY_DESC, MONEY_ASC,
        HOURS_DESC, HOURS_ASC
    }

    public enum SortRadnici {
        TOTAL_HOURS_DESC, TOTAL_HOURS_ASC
    }

    public static class TroskoviPdfRequest {
        public Scope scope;                 // ONE | ALL
        public AnimalType animalType;       // bitno kad je ALL
        public CostMode mode;               // HOURS | MONEY | BOTH

        public Long idJedinke;              // za ONE
        public Long idSkupine;              // za ONE

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        public LocalDate dateFrom;          // opcionalno

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        public LocalDate dateTo;            // opcionalno

        public SortTroskovi sort;           // samo kad je ALL
    }

    public static class RadniciPdfRequest {
        public Scope scope;                 // ONE | ALL
        public Long radnikId;               // za ONE

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        public LocalDate dateFrom;          // opcionalno

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        public LocalDate dateTo;            // opcionalno

        public SortRadnici sort;            // samo kad je ALL
    }
}
