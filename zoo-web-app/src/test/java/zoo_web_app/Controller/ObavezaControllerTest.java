package zoo_web_app.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import zoo_web_app.Entity.Obaveza;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.ObavezaService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = ObavezaController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
        }
)
@Import(GlobalExceptionHandler.class)
public class ObavezaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ObavezaService obavezaService;

    @Test
    void getAll_shouldReturn200AndList() throws Exception {

        Obaveza o = new Obaveza();
        o.setId(1L);

        when(obavezaService.findAll()).thenReturn(List.of(o));

        mockMvc.perform(get("/api/obaveza"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(obavezaService).findAll();
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(obavezaService.findById(id))
                .thenThrow(new ResourceNotFoundException("Obaveza ne postoji: " + id));

        mockMvc.perform(get("/api/obaveza/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Obaveza ne postoji: " + id));

        verify(obavezaService).findById(id);
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;

        doNothing().when(obavezaService).delete(id);

        mockMvc.perform(delete("/api/obaveza/{id}", id))
                .andExpect(status().isNoContent());

        verify(obavezaService).delete(id);
    }
}
