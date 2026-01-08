package zoo_web_app.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import zoo_web_app.Entity.GrupaPosjetitelja;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.GrupaPosjetiteljaService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = GrupaPosjetiteljaController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
        }
)
@Import(GlobalExceptionHandler.class)
class GrupaPosjetiteljaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GrupaPosjetiteljaService grupaPosjetiteljaService;

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        GrupaPosjetitelja g = new GrupaPosjetitelja();
        g.setId(1L);

        when(grupaPosjetiteljaService.findAll()).thenReturn(List.of(g));

        mockMvc.perform(get("/api/grupaPosjetitelja"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(grupaPosjetiteljaService).findAll();
    }

    @Test
    void getById_whenFound_shouldReturnGrupa() throws Exception {
        Long id = 1L;
        GrupaPosjetitelja g = new GrupaPosjetitelja();
        g.setId(id);

        when(grupaPosjetiteljaService.findById(id)).thenReturn(g);

        mockMvc.perform(get("/api/grupaPosjetitelja/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

        verify(grupaPosjetiteljaService).findById(id);
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(grupaPosjetiteljaService.findById(id))
                .thenThrow(new ResourceNotFoundException("Grupa ne postoji: " + id));

        mockMvc.perform(get("/api/grupaPosjetitelja/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Grupa ne postoji: " + id));

        verify(grupaPosjetiteljaService).findById(id);
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;
        doNothing().when(grupaPosjetiteljaService).delete(id);

        mockMvc.perform(delete("/api/grupaPosjetitelja/{id}", id))
                .andExpect(status().isNoContent());

        verify(grupaPosjetiteljaService).delete(id);
    }
}
