package zoo_web_app.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import zoo_web_app.Entity.Nastamba;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.NastambaService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NastambaController.class)
@Import(GlobalExceptionHandler.class)
public class NastambaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NastambaService nastambaService; // SAMO INTERFEJS

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        Nastamba n = new Nastamba();
        n.setId(1L);

        when(nastambaService.findAll()).thenReturn(List.of(n));

        mockMvc.perform(get("/api/nastambe"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(nastambaService).findAll();
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(nastambaService.findById(id))
                .thenThrow(new ResourceNotFoundException("Nastamba ne postoji: " + id));

        mockMvc.perform(get("/api/nastambe/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Nastamba ne postoji: " + id));

        verify(nastambaService).findById(id);
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;

        doNothing().when(nastambaService).delete(id);

        mockMvc.perform(delete("/api/nastambe/{id}", id))
                .andExpect(status().isNoContent());

        verify(nastambaService).delete(id);
    }
}
