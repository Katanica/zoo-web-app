package zoo_web_app.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import zoo_web_app.Entity.Incident;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.IncidentService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IncidentController.class)
@Import(GlobalExceptionHandler.class)
class IncidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IncidentService incidentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        Incident i = new Incident();
        i.setId(1L);

        when(incidentService.findAll()).thenReturn(List.of(i));

        mockMvc.perform(get("/api/incident"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(incidentService).findAll();
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(incidentService.findById(id))
                .thenThrow(new ResourceNotFoundException("Incident nije pronađen: " + id));

        mockMvc.perform(get("/api/incident/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Incident nije pronađen: " + id))
                .andExpect(jsonPath("$.timestamp").exists());

        verify(incidentService).findById(id);
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;

        doNothing().when(incidentService).delete(id);

        mockMvc.perform(delete("/api/incident/{id}", id))
                .andExpect(status().isNoContent());

        verify(incidentService).delete(id);
    }
}
