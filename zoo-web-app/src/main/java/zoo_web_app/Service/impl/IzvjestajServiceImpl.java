package zoo_web_app.Service.impl;


import org.openpdf.text.FontFactory;
import org.springframework.stereotype.Service;
import zoo_web_app.Controller.IzvjestajController;
import zoo_web_app.Entity.*;
import zoo_web_app.Repository.*;
import zoo_web_app.Service.IzvjestajService;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.openpdf.text.Document;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;



@Service
public class IzvjestajServiceImpl implements IzvjestajService {

    private final ObavezaRepository obavezaRepository;
    private final TrosakRepository trosakRepository;
    private final JedinkaRepository jedinkaRepository;
    private final SkupinaRepository skupinaRepository;
    private final RadnikRepository radnikRepository;

    public IzvjestajServiceImpl(ObavezaRepository obavezaRepository, TrosakRepository trosakRepository,
                                SkupinaRepository skupinaRepository, JedinkaRepository jedinkaRepository,
                                RadnikRepository radnikRepository) {
        this.obavezaRepository = obavezaRepository;
        this.trosakRepository = trosakRepository;
        this.jedinkaRepository = jedinkaRepository;
        this.skupinaRepository = skupinaRepository;
        this.radnikRepository = radnikRepository;
    }

    @Override
    public byte[] generirajTroskoviPdf(IzvjestajController.TroskoviPdfRequest req) {

        // 1) validacije (kratko)
        if (req == null || req.scope == null || req.mode == null) {
            throw new RuntimeException("Neispravan zahtjev.");
        }

        // 2) odredi period (ako nema datuma, uzmi sve)
        LocalDate od = req.dateFrom;
        LocalDate to = req.dateTo;

        // 3) pripremi listu rezultata (jedan red = jedna životinja/skupina)
        List<TrosakRed> redovi = new ArrayList<>();

        if (req.scope == IzvjestajController.Scope.ONE) {
            // ONE: izvještaj za jednu jedinku ili skupinu
            if (req.idJedinke == null && req.idSkupine == null) {
                throw new RuntimeException("Moraš poslati idJedinke ili idSkupine.");
            }

            TrosakRed r = izracunajTrosakJednog(req.idJedinke, req.idSkupine, od, to);
            redovi.add(r);

        } else {

            if(req.animalType == IzvjestajController.AnimalType.JEDINKA) {
                List<Jedinka> jedinke = jedinkaRepository.findByAktivnaTrue();
                for (Jedinka j : jedinke) {
                    TrosakRed r = izracunajTrosakJednog(j.getId(), null, od, to);
                    redovi.add(r);
                }
            }
            else if(req.animalType == IzvjestajController.AnimalType.SKUPINA) {
                List<Skupina> skupine = skupinaRepository.findByAktivnaTrue();
                for (Skupina s : skupine) {
                    TrosakRed r = izracunajTrosakJednog(null, s.getId(), od, to);
                    redovi.add(r);
                }
            }
        }
        if(req.sort == IzvjestajController.SortTroskovi.MONEY_ASC) // double - totalMoney, long - totalMinutes
            redovi.sort((a, b) -> Double.compare(a.totalMoney, b.totalMoney));
        else if(req.sort == IzvjestajController.SortTroskovi.MONEY_DESC)
            redovi.sort((a, b) -> Double.compare(b.totalMoney, a.totalMoney));
        else if(req.sort == IzvjestajController.SortTroskovi.HOURS_ASC)
            redovi.sort((a, b) -> Double.compare(a.totalMinutes, b.totalMinutes));
        else if(req.sort == IzvjestajController.SortTroskovi.HOURS_DESC)
            redovi.sort((a, b) -> Double.compare(b.totalMinutes, a.totalMinutes));

        // 4) napravi PDF
        return napraviTroskoviPdf(req, redovi);
    }

    @Override
    public byte[] generirajRadniciPdf(IzvjestajController.RadniciPdfRequest req) {

        LocalDate od = req.dateFrom;
        LocalDate to = req.dateTo;

        List<TrosakRed> redovi = new ArrayList<>();

        if (req.scope == IzvjestajController.Scope.ONE) {
            // ONE: izvještaj za jednu jedinku ili skupinu
            if (req.radnikId == null) {
                throw new RuntimeException("Moraš poslati id radnika.");
            }

            String ime = radnikRepository.findImeById(req.radnikId);
            String prezime = radnikRepository.findPrezimeById(req.radnikId);

            TrosakRed r = izracunajTrosakJednogRadnika(req.radnikId, ime, prezime, od, to);
            redovi.add(r);

        } else {
            for(Radnik r : radnikRepository.findAll()){
                TrosakRed red = izracunajTrosakJednogRadnika(r.getId(), r.getIme(), r.getPrezime(), od, to);
                redovi.add(red);
            }
        }

        // sortiranje po želji
        if(req.sort == IzvjestajController.SortRadnici.TOTAL_HOURS_DESC)
            redovi.sort((a, b) -> Long.compare(b.totalMinutes, a.totalMinutes));    // DESC
        else
            redovi.sort((a, b) -> Long.compare(a.totalMinutes, b.totalMinutes));    // ASCENDIND

        // 4) napravi PDF
        return napraviRadniciPdf(req, redovi);
    }

    private TrosakRed izracunajTrosakJednogRadnika(Long idRadnika, String ime, String prezime, LocalDate od, LocalDate to){
        List<Obaveza> obaveze = obavezaRepository.findByRadnikId(idRadnika);

        long totalMinutes = obaveze.stream()
                .mapToLong(o -> o.getTrajanje() == null ? 0 : o.getTrajanje())
                .sum();

        TrosakRed tr = new TrosakRed();
        tr.naziv = ime + " " + prezime;
        tr.totalMinutes = totalMinutes;

        return tr;
    }

    private TrosakRed izracunajTrosakJednog(Long idJedinke, Long idSkupine, LocalDate od, LocalDate to) {

        // 1) radni sati (iz Obaveza)
        List<Obaveza> obaveze;

        // dobavi obaveze ili jedinke ili skupine
        if (idJedinke != null) {
            obaveze = obavezaRepository.findByJedinkaId(idJedinke);
        } else {
            obaveze = obavezaRepository.findBySkupinaId(idSkupine);
        }

        // filter datuma ako postoji
        if (od != null && to != null) {
            obaveze = obaveze.stream()
                    .filter(o -> o.getVrijemeOd() != null)
                    .filter(o -> {
                        LocalDate d = o.getVrijemeOd().toLocalDate();
                        return (!d.isBefore(od) && !d.isAfter(to));
                    })
                    .collect(Collectors.toList());
        }

        long totalMinutes = obaveze.stream()
                .mapToLong(o -> o.getTrajanje() == null ? 0 : o.getTrajanje())
                .sum();

        // 2) novac (iz Trosak)
        List<Trosak> troskovi;

        if (idJedinke != null) {
            troskovi = trosakRepository.findByJedinkaId(idJedinke);
        } else {
            troskovi = trosakRepository.findBySkupinaId(idSkupine);
        }

        if (od != null && to != null) {
            troskovi = troskovi.stream()
                    .filter(t -> t.getDatum() != null)
                    .filter(t -> (!t.getDatum().isBefore(od) && !t.getDatum().isAfter(to)))
                    .collect(Collectors.toList());
        }

        double totalMoney = troskovi.stream()
                .mapToDouble(t -> t.getIznos() == null ? 0.0 : t.getIznos())
                .sum();

        // 3) složi red
        TrosakRed r = new TrosakRed();
        r.naziv = (idJedinke != null) ? jedinkaRepository.findById(idJedinke).orElseThrow().sastaviIme() :
                (skupinaRepository.findById(idSkupine).orElseThrow().sastaviIme());
        r.totalMinutes = totalMinutes;
        r.totalMoney = totalMoney;
        return r;
    }

    private String isoDate(LocalDate datum){
        return datum.getDayOfMonth() + "." + datum.getMonthValue() + "." + datum.getYear() + ".";
    }

    private byte[] napraviRadniciPdf(IzvjestajController.RadniciPdfRequest req, List<TrosakRed> redovi){
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, out);
            doc.open();

            doc.add(new Paragraph("IZVJEŠTAJ TROŠKOVA", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            doc.add(new Paragraph("Scope: " + req.scope));
            doc.add(new Paragraph("Datum: " + isoDate(req.dateFrom) + " - " + isoDate(req.dateTo)));
            doc.add(new Paragraph(" "));

            PdfPTable table;

            table = new PdfPTable(2);
            table.addCell("Subjekt");
            table.addCell("Radni sati");

            for (TrosakRed r : redovi) {
                table.addCell(r.naziv);
                table.addCell(String.format(Locale.US, "%.2f h", r.totalMinutes / 60.0));
            }

            doc.add(table);
            doc.close();

            return out.toByteArray();
        }catch (Exception e) {
            throw new RuntimeException("Greška pri generiranju PDF-a za radnike: " + e.getMessage());
        }
    }

    private byte[] napraviTroskoviPdf(IzvjestajController.TroskoviPdfRequest req, List<TrosakRed> redovi) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, out);
            doc.open();

            doc.add(new Paragraph("IZVJEŠTAJ TROŠKOVA", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            doc.add(new Paragraph("Scope: " + req.scope));
            doc.add(new Paragraph("Mode: " + req.mode));
            doc.add(new Paragraph("Datum: " + isoDate(req.dateFrom) + " - " + isoDate(req.dateTo)));
            doc.add(new Paragraph(" "));

            // Tabela
            PdfPTable table;

            if (req.mode == IzvjestajController.CostMode.HOURS) {
                table = new PdfPTable(2);
                table.addCell("Subjekt");
                table.addCell("Radni sati");

                for (TrosakRed r : redovi) {
                    table.addCell(r.naziv);
                    table.addCell(String.format(Locale.US, "%.2f h", r.totalMinutes / 60.0));
                }

            } else if (req.mode == IzvjestajController.CostMode.MONEY) {
                table = new PdfPTable(2);
                table.addCell("Subjekt");
                table.addCell("Novac");

                for (TrosakRed r : redovi) {
                    table.addCell(r.naziv);
                    table.addCell(String.format(Locale.US, "%.2f", r.totalMoney));
                }

            } else {
                table = new PdfPTable(3);
                table.addCell("Subjekt");
                table.addCell("Radni sati");
                table.addCell("Novac");

                for (TrosakRed r : redovi) {
                    table.addCell(r.naziv);
                    table.addCell(String.format(Locale.US, "%.2f h", r.totalMinutes / 60.0));
                    table.addCell(String.format(Locale.US, "%.2f", r.totalMoney));
                }
            }

            doc.add(table);
            doc.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Greška pri generiranju PDF-a (životinje): " + e.getMessage());
        }
    }

    private byte[] napraviSimplePdf(String title) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, out);
            doc.open();
            doc.add(new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            doc.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Greška pri generiranju PDF-a.");
        }
    }

    // unutarnji helper "red"
    private static class TrosakRed {
        String naziv;
        long totalMinutes;
        double totalMoney;
    }
}
