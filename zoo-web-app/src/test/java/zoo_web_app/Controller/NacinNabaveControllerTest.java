package zoo_web_app.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zoo_web_app.Entity.NacinNabave;
import zoo_web_app.Exception.GlobalExceptionHandler;
import zoo_web_app.Exception.ResourceNotFoundException;
import zoo_web_app.Repository.NacinNabaveRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NacinNabaveControllerTest {

    private MockMvc mockMvc;
    private NacinNabaveRepository repository; // mock repo
    private NacinNabaveController controller;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        repository = mock(NacinNabaveRepository.class); // kreiramo mock
        controller = new NacinNabaveController(repository); // injektiramo u controller
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getAll_shouldReturn200AndList() throws Exception {
        NacinNabave n = new NacinNabave();
        n.setId(1L);

        when(repository.findAll()).thenReturn(List.of(n));

        mockMvc.perform(get("/api/nacin_nabave"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(repository).findAll();
    }

    @Test
    void getById_whenFound_shouldReturn200() throws Exception {
        Long id = 1L;
        NacinNabave n = new NacinNabave(id, "Test nabave");

        when(repository.findById(id)).thenReturn(Optional.of(n));

        mockMvc.perform(get("/api/nacin_nabave/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.opisNabave").value("Test nabave"));

        verify(repository).findById(id);
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Long id = 99L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/nacin_nabave/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Način nabave ne postoji: " + id))
                .andExpect(jsonPath("$.timestamp").exists());

        verify(repository).findById(id);
    }

    @Test
    void delete_whenExists_shouldReturn204() throws Exception {
        Long id = 1L;

        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);

        mockMvc.perform(delete("/api/nacin_nabave/{id}", id))
                .andExpect(status().isNoContent());

        verify(repository).existsById(id);
        verify(repository).deleteById(id);
    }

    @Test
    void delete_whenNotExists_shouldReturn404() throws Exception {
        Long id = 999L;

        when(repository.existsById(id)).thenReturn(false);

        mockMvc.perform(delete("/api/nacin_nabave/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Način nabave ne postoji: " + id))
                .andExpect(jsonPath("$.timestamp").exists());

        verify(repository).existsById(id);
    }
}
