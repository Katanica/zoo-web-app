package zoo_web_app.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import zoo_web_app.Entity.Karakteristika;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.KarakteristikaService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = KarakteristikaController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
        }
)
@Import(GlobalExceptionHandler.class)
class KarakteristikaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private KarakteristikaService karakteristikaService;

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        Karakteristika k = new Karakteristika();
        k.setId(1L);

        when(karakteristikaService.findAll()).thenReturn(List.of(k));

        mockMvc.perform(get("/api/karakteristike"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(karakteristikaService).findAll();
    }

    @Test
    void getById_whenFound_shouldReturnKarakteristika() throws Exception {
        Long id = 1L;
        Karakteristika k = new Karakteristika();
        k.setId(id);

        when(karakteristikaService.findById(id)).thenReturn(k);

        mockMvc.perform(get("/api/karakteristike/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

        verify(karakteristikaService).findById(id);
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(karakteristikaService.findById(id))
                .thenThrow(new ResourceNotFoundException("Karakteristika ne postoji: " + id));

        mockMvc.perform(get("/api/karakteristike/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Karakteristika ne postoji: " + id));

        verify(karakteristikaService).findById(id);
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;
        doNothing().when(karakteristikaService).delete(id);

        mockMvc.perform(delete("/api/karakteristike/{id}", id))
                .andExpect(status().isNoContent());

        verify(karakteristikaService).delete(id);
    }
}
