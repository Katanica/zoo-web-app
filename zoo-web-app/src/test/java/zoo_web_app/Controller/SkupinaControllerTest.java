package zoo_web_app.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zoo_web_app.Entity.Skupina;
import zoo_web_app.Repository.SkupinaRepository;
import zoo_web_app.Service.SkupinaService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SkupinaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SkupinaService skupinaService;

    @Mock
    private SkupinaRepository skupinaRepository;

    @InjectMocks
    private SkupinaController skupinaController;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private AutoCloseable closeable;

    private Skupina testSkupina;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(skupinaController).build();

        testSkupina = new Skupina();
        testSkupina.setId(1L);
        // postavi samo ono što stvarno imaš u entitetu
        // ako imaš setNaziv() → koristi to
        // ako imaš setHrvatskiJezik() → koristi to, ali mora imati i getter!
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void getAll_shouldReturnList() throws Exception {
        List<Skupina> list = Arrays.asList(testSkupina);
        when(skupinaService.findAll()).thenReturn(list);

        mockMvc.perform(get("/api/skupine"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void getById_shouldReturnSkupina() throws Exception {
        when(skupinaService.findById(1L)).thenReturn(testSkupina);

        mockMvc.perform(get("/api/skupine/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void create_shouldReturnCreatedSkupina() throws Exception {
        when(skupinaService.create(any(Skupina.class))).thenReturn(testSkupina);

        mockMvc.perform(post("/api/skupine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSkupina)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void update_shouldReturnUpdatedSkupina() throws Exception {
        when(skupinaService.update(eq(1L), any(Skupina.class))).thenReturn(testSkupina);

        mockMvc.perform(put("/api/skupine/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSkupina)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void delete_shouldReturnNoContent() throws Exception {
        doNothing().when(skupinaService).delete(1L);

        mockMvc.perform(delete("/api/skupine/1"))
                .andExpect(status().isNoContent());

        verify(skupinaService, times(1)).delete(1L);
    }

    @Test
    public void aktivne_shouldReturnList() throws Exception {
        List<Skupina> list = Arrays.asList(testSkupina);
        when(skupinaRepository.findAllAktivne()).thenReturn(list);

        mockMvc.perform(get("/api/skupine/aktivne"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));
    }
}