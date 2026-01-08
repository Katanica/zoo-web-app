package zoo_web_app.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import zoo_web_app.Entity.RadNaIncidentu;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.RadNaIncidentuService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = RadNaIncidentuController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
        }
)
@Import(GlobalExceptionHandler.class)
class RadNaIncidentuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RadNaIncidentuService radService;

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        RadNaIncidentu r = new RadNaIncidentu();
        r.setId(1L);

        when(radService.findAll()).thenReturn(List.of(r));

        mockMvc.perform(get("/api/rad-incident"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(radService).findAll();
    }

    @Test
    void getById_whenFound_shouldReturnRad() throws Exception {
        Long id = 1L;
        RadNaIncidentu r = new RadNaIncidentu();
        r.setId(id);

        when(radService.findById(id)).thenReturn(r);

        mockMvc.perform(get("/api/rad-incident/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

        verify(radService).findById(id);
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(radService.findById(id))
                .thenThrow(new ResourceNotFoundException("RadNaIncidentu ne postoji: " + id));

        mockMvc.perform(get("/api/rad-incident/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("RadNaIncidentu ne postoji: " + id));

        verify(radService).findById(id);
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;
        doNothing().when(radService).delete(id);

        mockMvc.perform(delete("/api/rad-incident/{id}", id))
                .andExpect(status().isNoContent());

        verify(radService).delete(id);
    }

    @Test
    void findByIncident_shouldReturnList() throws Exception {
        Long incidentId = 10L;
        RadNaIncidentu r = new RadNaIncidentu();
        r.setId(1L);

        when(radService.findByIncident(incidentId)).thenReturn(List.of(r));

        mockMvc.perform(get("/api/rad-incident/incident/{incidentId}", incidentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));

        verify(radService).findByIncident(incidentId);
    }
}
