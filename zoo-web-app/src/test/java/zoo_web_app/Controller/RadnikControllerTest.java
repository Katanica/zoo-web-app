package zoo_web_app.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import zoo_web_app.Entity.Radnik;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.RadnikService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RadnikController.class)
@Import(GlobalExceptionHandler.class)
class RadnikControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RadnikService radnikService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        Radnik r = new Radnik();
        r.setId(1L);
        r.setIme("Ivan");
        r.setPrezime("Ivic");

        when(radnikService.findAll()).thenReturn(List.of(r));

        mockMvc.perform(get("/api/radnik"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].ime").value("Ivan"))
                .andExpect(jsonPath("$[0].prezime").value("Ivic"));

        verify(radnikService).findAll();
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(radnikService.findById(id))
                .thenThrow(new ResourceNotFoundException("Radnik nije pronađen: " + id));

        mockMvc.perform(get("/api/radnik/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Radnik nije pronađen: " + id))
                .andExpect(jsonPath("$.timestamp").exists());

        verify(radnikService).findById(id);
    }

    @Test
    void getBroj_shouldReturn200AndNumber() throws Exception {
        when(radnikService.brojRadnika()).thenReturn(5L);

        mockMvc.perform(get("/api/radnik/broj"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

        verify(radnikService).brojRadnika();
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;
        doNothing().when(radnikService).delete(id);

        mockMvc.perform(delete("/api/radnik/{id}", id))
                .andExpect(status().isNoContent());

        verify(radnikService).delete(id);
    }
}
