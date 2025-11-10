package zoo_web_app.Controller;

import zoo_web_app.Entity.Zaposlenik;
import zoo_web_app.Service.ZaposlenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/zaposlenici")
public class ZaposlenikController {

    @Autowired
    private ZaposlenikService Service;

    @GetMapping
    public String list(@RequestParam(required = false) String kvalifikacija, Model model) {
        if (kvalifikacija != null && !kvalifikacija.isEmpty()) {
            model.addAttribute("zaposlenici", Service.filterByKvalifikacija(kvalifikacija));
            model.addAttribute("filter", kvalifikacija);
        } else {
            model.addAttribute("zaposlenici", Service.findAll());
        }
        return "zaposlenici/list";
    }

    @GetMapping("/nova")
    public String novaForma(Model model) {
        model.addAttribute("zaposlenik", new Zaposlenik());
        return "zaposlenici/forma";
    }

    @PostMapping("/spremi")
    public String spremi(@ModelAttribute Zaposlenik zaposlenik) {
        Service.save(zaposlenik);
        return "redirect:/zaposlenici";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        Service.deleteById(id);
        return "redirect:/zaposlenici";
    }
}