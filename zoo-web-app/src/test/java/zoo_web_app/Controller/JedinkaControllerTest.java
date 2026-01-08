package zoo_web_app.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import zoo_web_app.Entity.Jedinka;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.JedinkaService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JedinkaController.class)
@Import(GlobalExceptionHandler.class)
public class JedinkaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JedinkaService jedinkaService; // SAMO JEDAN

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        Jedinka j = new Jedinka();
        j.setId(1L);

        when(jedinkaService.findAll()).thenReturn(List.of(j));

        mockMvc.perform(get("/api/jedinke"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(jedinkaService).findAll();
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(jedinkaService.findById(id))
                .thenThrow(new ResourceNotFoundException("Jedinka ne postoji: " + id));

        mockMvc.perform(get("/api/jedinke/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Jedinka ne postoji: " + id));

        verify(jedinkaService).findById(id);
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;

        doNothing().when(jedinkaService).delete(id);

        mockMvc.perform(delete("/api/jedinke/{id}", id))
                .andExpect(status().isNoContent());

        verify(jedinkaService).delete(id);
    }
}
