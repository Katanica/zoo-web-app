package zoo_web_app.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zoo_web_app.DTO.RasporedRasponZahtjev;
import zoo_web_app.Entity.Raspored;
import zoo_web_app.Entity.Smjena;
import zoo_web_app.Entity.VrstaUnosaRasporeda;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Service.RasporedService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RasporedController.class)
@Import(GlobalExceptionHandler.class)
public class RasporedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @org.springframework.test.context.bean.override.mockito.MockitoBean
    private RasporedService rasporedService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        Raspored r = new Raspored();
        r.setId(1L);

        when(rasporedService.findAll()).thenReturn(List.of(r));

        mockMvc.perform(get("/api/raspored"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));

        verify(rasporedService).findAll();
    }

    @Test
    void getByPonedjeljak_shouldReturn200() throws Exception {
        LocalDate ponedjeljak = LocalDate.of(2026, 1, 5);
        Raspored r = new Raspored();
        r.setId(1L);

        when(rasporedService.dohvatiPoPonedjeljku(ponedjeljak)).thenReturn(List.of(r));

        mockMvc.perform(get("/api/raspored/tjedan")
                        .param("ponedjeljak", ponedjeljak.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void getByRadnik_shouldReturn200() throws Exception {
        LocalDate ponedjeljak = LocalDate.of(2026, 1, 5);
        Raspored r = new Raspored();
        r.setId(1L);

        when(rasporedService.dohvatiRasporedZaRadnikaUTjednu(1L, ponedjeljak))
                .thenReturn(List.of(r));

        mockMvc.perform(get("/api/raspored/radnik")
                        .param("radnikId", "1")
                        .param("ponedjeljak", ponedjeljak.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));
    }
    //
    @Test
    void spremiDan_saSve_shouldCallServiceAndReturn200() throws Exception {
        LocalDate datum = LocalDate.of(2026, 1, 6);

        mockMvc.perform(post("/api/raspored/dan")
                        .param("radnikId", "1")
                        .param("vrstaUnosa", "RAD")
                        .param("smjena", "PRVA")
                        .param("datum", datum.toString()))
                .andExpect(status().isOk());

        verify(rasporedService).spremiJedanDan(
                eq(1L),
                eq(VrstaUnosaRasporeda.RAD),
                eq(Smjena.PRVA),
                eq(datum)
        );
    }

    @Test
    void spremiDan_bezSmjene_shouldCallServiceWithNullSmjena() throws Exception {
        LocalDate datum = LocalDate.of(2026, 1, 7);

        mockMvc.perform(post("/api/raspored/dan")
                        .param("radnikId", "2")
                        .param("vrstaUnosa", "SLOBODNO")
                        .param("datum", datum.toString()))
                .andExpect(status().isOk());

        verify(rasporedService).spremiJedanDan(
                eq(2L),
                eq(VrstaUnosaRasporeda.SLOBODNO),
                isNull(),
                eq(datum)
        );
    }

    @Test
    void spremiRaspon_shouldCallServiceAndReturn200() throws Exception {
        RasporedRasponZahtjev zahtjev = new RasporedRasponZahtjev();
        zahtjev.radnikId = 1L;
        zahtjev.vrstaUnosa = VrstaUnosaRasporeda.RAD;
        zahtjev.smjena = Smjena.PRVA;
        zahtjev.datumOd = LocalDate.of(2026, 1, 5);
        zahtjev.datumDo = LocalDate.of(2026, 1, 11);

        String json = objectMapper.writeValueAsString(zahtjev);

        mockMvc.perform(post("/api/raspored/raspon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(rasporedService).spremiRaspon(
                eq(1L),
                eq(VrstaUnosaRasporeda.RAD),
                eq(Smjena.PRVA),
                eq(LocalDate.of(2026, 1, 5)),
                eq(LocalDate.of(2026, 1, 11))
        );
    }

    @Test
    void delete_shouldReturn200AndCallService() throws Exception {
        Long id = 5L;
        doNothing().when(rasporedService).obrisi(id);

        mockMvc.perform(delete("/api/raspored/{id}", id))
                .andExpect(status().isOk());

        verify(rasporedService).obrisi(id);
    }
}
