package zoo_web_app.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import zoo_web_app.Entity.Infrastruktura;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Service.InfrastrukturaService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InfrastrukturaController.class)
@Import(GlobalExceptionHandler.class)
public class InfrastrukturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InfrastrukturaService infrastrukturaService;

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        Infrastruktura i = new Infrastruktura();
        i.setId(1L);

        when(infrastrukturaService.findAll()).thenReturn(List.of(i));

        mockMvc.perform(get("/api/infrastruktura"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(infrastrukturaService).findAll();
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(infrastrukturaService.findById(id))
                .thenThrow(new ResourceNotFoundException("Infrastruktura nije pronađena: " + id));

        mockMvc.perform(get("/api/infrastruktura/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Infrastruktura nije pronađena: " + id));

        verify(infrastrukturaService).findById(id);
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        Long id = 1L;

        doNothing().when(infrastrukturaService).delete(id);

        mockMvc.perform(delete("/api/infrastruktura/{id}", id))
                .andExpect(status().isNoContent());

        verify(infrastrukturaService).delete(id);
    }
}
