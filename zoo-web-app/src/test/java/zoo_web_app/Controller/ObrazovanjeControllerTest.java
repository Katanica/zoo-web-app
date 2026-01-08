package zoo_web_app.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import zoo_web_app.Entity.Obrazovanje;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.ObrazovanjeService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = ObrazovanjeController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
        }
)
@Import(GlobalExceptionHandler.class)
class ObrazovanjeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ObrazovanjeService obrazovanjeService;

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        Obrazovanje o = new Obrazovanje();
        o.setId(1L);

        when(obrazovanjeService.findAll()).thenReturn(List.of(o));

        mockMvc.perform(get("/api/obrazovanje"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(obrazovanjeService).findAll();
    }

    @Test
    void getById_whenFound_shouldReturnObrazovanje() throws Exception {
        Long id = 1L;
        Obrazovanje o = new Obrazovanje();
        o.setId(id);

        when(obrazovanjeService.findById(id)).thenReturn(o);

        mockMvc.perform(get("/api/obrazovanje/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

        verify(obrazovanjeService).findById(id);
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(obrazovanjeService.findById(id))
                .thenThrow(new ResourceNotFoundException("Obrazovanje ne postoji: " + id));

        mockMvc.perform(get("/api/obrazovanje/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Obrazovanje ne postoji: " + id));

        verify(obrazovanjeService).findById(id);
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;
        doNothing().when(obrazovanjeService).delete(id);

        mockMvc.perform(delete("/api/obrazovanje/{id}", id))
                .andExpect(status().isNoContent());

        verify(obrazovanjeService).delete(id);
    }
}
